# 分布式存在的并发问题
## 问题模拟
商品购买,在单机时并发问题可以通过synchronized,
但是在分布式情况下,JVM级别的锁不会生效.

假设: redis存了个string [stock:200]

```
@GetMapping("/buy")
public String buy(){
    synchronized (this){
        String s = redisUtils.get("stock").toString();
        Integer i = Integer.parseInt(s);
        if(i>0){
            int real = i-1;
            redisUtils.set("stock",real+"");
            logger.info("购买成功,剩余:"+real);
        }else{
            logger.info("购买失败");
        }
    }
    return "buy end";
}
```
1. 启动两个服务product 8081/8082,并使用Nginx做负载均衡
```
 # 待选服务器列表
   worker_processes  1;

   events {
   worker_connections  1024;
}


   http {
   upstream  my-server {
   server    localhost:8081 weight=1;
   server    localhost:8082 weight=1;
   }

   server {
   listen       80;
   server_name  localhost;

   location / {
   proxy_pass http://my-server;
   proxy_redirect default;
   }

}

}
```
2. 使用jmeter进行压力测试
> http://127.0.0.1/buy

可以看到后台打印的剩余库存出现了同样的数字.

## 解决
### 入门级 setnx (set if not exists)
setnx key value 将key的值设为value,当且仅当key不存在

利用redis单线程模型,若干线程同时执行setnx,会进入队列.
```
    @GetMapping("/buy2")
    public String buy2() {
        String lockKey = "product_01";
        // setIfAbsent原子命令
        Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, "zong",10,TimeUnit.SECONDS);//同时设置过期时间
        if (!result) {
            return "error";
        }
        try {
            Integer i = Integer.parseInt(redisUtils.get("stock").toString());

            if (i > 0) {
                int real = i - 1;
                redisUtils.set("stock", real + "");
                logger.info("购买成功,剩余:" + real);
            } else {
                logger.info("购买失败");
            }
        } finally {
            redisTemplate.delete(lockKey);
        }
        return "buy end";
    }
```

高并发仍然存在的问题:
锁永久失效:假设线程1try后面的代码执行时间超过了锁的过期时间,此时线程2进入并加锁,但线程1会执行到释放锁的地方,
导致线程1删除了线程2的锁.
### 优化:(添加锁需要原子操作)每个线程加的锁的value都设为UUID,释放锁的时候进行判断
```
    String id  = UUID.randomUUID().toString();
    // setIfAbsent原子命令
    Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, id,10,TimeUnit.SECONDS);//同时设置过期时间
    //...
    if (id.equals(redisTemplate.opsForValue().get(lockKey))){
        redisTemplate.delete(lockKey);
    }
```
仍然存在的问题:线程1在判断成功进入的那一瞬间(还没有执行锁的释放),
锁过期了,线程2又刚加了锁,仍然导致线程1释放了线程2的锁

### 解决:锁续命(删除锁也需要原子操作)
再开一个定时任务,判断主线程的锁是否还持有该锁,若锁过期且主线程还没结束,就刷新锁的过期时间.
使用分布式锁框架解决该问题.

### Redission
#### 依赖
```
    <dependency>
        <groupId>org.redisson</groupId>
        <artifactId>redisson</artifactId>
        <version>3.6.5</version>
    </dependency>
```
#### 配置
```
    @Bean
    public Redisson redisson(){
        //单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);

    }
   ```
#### 加锁和释放
```
    @Autowired
    Redisson redisson;

    @GetMapping("/buy3")
    public String buy3() {
        String lockKey = "product_01";
        RLock lock = redisson.getLock(lockKey);
        try {
            lock.lock();//真正的加锁 setIfAbsent 30s
            Integer i = Integer.parseInt(redisUtils.get("stock").toString());
            if (i > 0) {
                int real = i - 1;
                redisUtils.set("stock", real + "");
                logger.info("购买成功,剩余:" + real);
            } else {
                logger.info("购买失败");
            }
        } finally {
            lock.unlock();//
        }
        return "buy end";
    }
   ```
redisson大致逻辑(lua脚本实现):
1. 线程1加锁成功(30s),
开启后台线程(每个10s检测线程1是否还持有锁,如果有则延长10s)
2. 线程2判断是否能加锁,不能则自旋等待,知道能为止
3. 线程1释放锁,线程2加锁


### Redis主从架构导致的分布式锁失效问题
客户端加锁拿到主节点的返回后,主节点还未同步给从节点就宕机了,导致从节点没有拿到客户端的锁.

> CAP原则:一致性/可用性/分区容错性,三者不可能同时存在.

- Redis满足AP:一致性/分区容错性,Redis强调可用性,会立即返回结果.
- Zookeeper满足CP:一致性/分区容错性,同样的,Zookeeper在客户端加锁后不会立即返回结果,
  只有Zookeeper集群半数以上都获得同步后,才会返回客户端成功(过半写)

#### Redlock
Redis对于此问题,也可以使用Redlock来解决.
Redlock针对的是客户端,客户端加锁后,超过半数以上的节点加锁成功才算成功.(牺牲可用性,不如直接用zookeeper)


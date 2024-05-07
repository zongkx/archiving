# RabbitMQ的五种策略

## 依赖和配置

```yml
spring:
  application:
    name: consumer
  rabbitmq:
    virtual-host: / #创建不同的虚拟主机,方便不同的应用使用
    username: username
    password: password
    port: 5672
    addresses: 39.97.243.43
```

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```



## 直连

一个默认交换机的单播路由，并且每个队列只有一个消费者

1. 生产者

```java
amqpTemplate.convertAndSend("hello","hello world");
```

消息生产者创建消息后并不会出现对应的Queue,只有在消费者消费时才会出现对应的Queue,该模式创建的Queue默认是持久化的(Features:D),非独占,非自动删除的.

2. 消费者

```java
@Component
@RabbitListener(queuesToDeclare = @Queue("hello"))//该注解也可用以方法
public class MQ1 {
    @RabbitHandler
    public void receive(String msg){
        System.out.println("这是直连模式消费者:"+msg);
    }
}
```

Queue:

- durable="true" : 持久化的
- autoDelete="false":非自动删除
-  exclusive="false":非独占

## 工作队列

默认交换机的单播路由，并且每个队列有多个消费者

生产者发消息,多个消费者收消息(公平的发给不同的消费者)

1. 生产者一次生产两条消息

```java
amqpTemplate.convertAndSend("work","work");
amqpTemplate.convertAndSend("work","work");
```

2. 消费者

消费者将会公平的获取消息,消费者总会公平的消费.

```java
	@RabbitListener(queuesToDeclare = @Queue(value = "work" ))
    public void receive1(String msg){
        System.out.println("这是工作队列模式消费者1:"+msg);
    }
    @RabbitListener(queuesToDeclare = @Queue(value = "work" ))
    public void receive2(String msg){
        System.out.println("这是工作队列模式消费者2:"+msg);
    }
```

能者多劳策略需要额外的配置

## 发布订阅

扇形交换机的多播路由,生产者向交换机发布消息,利用交换机广播给各个消费队列.(每个消费者对应一个队列)

1. 生产者

   ```java
   //跟路由key没有关系,第二个参数为空
   rabbitTemplate.convertAndSend("logs","","Fanout模型发送的消息");
   //运行后并不会出现对应的exchange:logs,需要消费者触发
   ```

2. 消费者

```java
@RabbitListener(bindings = @QueueBinding(
        value = @Queue,//绑定临时队列(没有加name属性)
        exchange = @Exchange(value = "logs",type = "fanout")//绑定交换机logs
))
public void receive1(String msg){
    System.out.println("这是广播模式消费者1:"+msg);
}

@RabbitListener(bindings = @QueueBinding(
        value = @Queue,//绑定临时队列(没有加name属性)
        exchange = @Exchange(value = "logs",type = "fanout")//绑定交换机logs
))
public void receive2(String msg){
    System.out.println("这是广播模式消费者2:"+msg);
}
```

## 路由模型

直连交换机的多播路由

相较于发布订阅模型,根据指定exchange的key向不同的临时队列发布消息

1. 生产者

```java
        rabbitTemplate.convertAndSend("direct","info",
                "发送info的key的exchange信息");
        rabbitTemplate.convertAndSend("direct","error",
                "发送error的key的exchange信息");
```

2. 消费者

```java
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,//绑定临时队列(没有加name属性)
            exchange = @Exchange(value = "direct",type = "direct"),//绑定交换机名称和类型
            key = {"info","error"}
    ))
    public void receive1(String msg){
        System.out.println("这是路由模式消费者(只消费来自info和error类型)1:"+msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,//绑定临时队列(没有加name属性)
            exchange = @Exchange(value = "direct",type = "direct"),//绑定交换机名称和类型
            key = {"info"}//对应到生产者的routingKey
    ))
    public void receive2(String msg){
        System.out.println("这是路由模式消费者(只消费来自info类型)2:"+msg);
    }
```

## 订阅模型(动态路由)

相较于路由模型,可以通过通配符*来实现动态路由的效果

1. 生产者

```java
rabbitTemplate.convertAndSend("topics","user.save", "user.save的路由消息");
```

2. 消费者,利用*/#进行动态路由

```java
@RabbitListener(bindings = @QueueBinding(
        value = @Queue,//绑定临时队列(没有加name属性)
        exchange = @Exchange(value = "topics",type = "topic"),//绑定交换机名称和类型(topic)
        key = {"user.save","user.*"}
))
public void receive1(String msg){
    System.out.println("这是动态路由模式消费者1:"+msg);
}

@RabbitListener(bindings = @QueueBinding(
        value = @Queue,//绑定临时队列(没有加name属性)
        exchange = @Exchange(value = "topics",type = "topic"),//绑定交换机名称和类型
        key = {"user.*","product.#"}//对应到生产者的routingKey
))
public void receive2(String msg){
    System.out.println("这是动态路由模式消费者2:"+msg);
}
```

# 消息投递可靠性

## 生产者可靠性

消息投递路径:

provider-->rabbitmq broker-->exchange-->queue-->consumer

- confirm模式:消息从provider到broker有一个 confirmCallback

- return 模式:消息从exchange--->queue,投递失败有一个returnCallback

具体到接口就是setConfirmCallback/setReturnCallback.



加入一下配置,开启生产者消息确认.

```yml
    publisher-returns: true
    publisher-confirm-type: correlated  #确认消息发送到交换机选择确认类型为交互
```

创建消息,利用RetryTemplate实现ack,setReturnCallback可以通过缓存来设置重发次数,下面用Redis简单计数.

1. 测试confirm:更改direct为错误的exchange名称:direct,便会打印发送失败,实际上需要根据缓存进行业务处理
2. 测试return:更改错误的routingKey,下面用redis模拟了重发.

```java
	@GetMapping("/mq6")
    public String publish6(String ans){
        try{
            rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
                if (!ack) {
                    System.out.println("发送失败:" + cause);
                }
            });
            // 确认是否发到队列，若没有则存缓存，然后检查exchange, routingKey配置，之后重发
            rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
                Object o = redisTemplate.opsForValue().get("num");
                if(o == null){//correlationData 相关配置新信息 cause:原因
                    redisTemplate.opsForValue().set("num",0);
                }else{
                    Integer i = Integer.parseInt(o.toString());
                    i ++;
                    if(i<3){
                        redisTemplate.opsForValue().set("num",i);
                        // 存缓存操作
                        System.out.println(new String(message.getBody()) + "找不到队列，exchange为" + exchange + ",routingKey为" + routingKey);
                        rabbitTemplate.convertAndSend("direct", "ack", ans);
                    }else{
                        System.out.println("1111");
                    }
                }
            });
            rabbitTemplate.convertAndSend("direct", "ack", ans);
        }catch (Exception e){
        }
        return Thread.currentThread().getName();
    }
```

## 消费者可靠性

消费端收到消息的确认方式

- 自动确认:none,异常会导致消息丢失(消息一旦被接受,msg便会被MQ移除缓存)
- 手动确认:manual
- 自动判断:auto,根据抛出的异常类进行判定



```yml
    listener: //spring.rabbit.listener
      direct:
        acknowledge-mode: manual
```

消费端代码如下

```java
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "ackQueue"),
        exchange = @Exchange(value = "direct",type = "direct"),//绑定交换机名称和类型
        key = {"ack"}//对应到生产者的routingKey
))
public void receive3(String msg,Message message, Channel channel)throws Exception {
    try {
        if("error".equals(msg)){
            int i = 1/0;
        }else{
            System.out.println("消费成功"+msg);
            //手动签收:消息处理成功后,调用 ack 告诉broker签收;
            // 第一个参数为消息的投递者签名;
            // 第二个参数为是否需要签收多个消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
     } catch (Exception e) {
        System.out.println("消费失败:" + msg);
        //消息处理失败,调用nack,签收失败,broker重新发送给consumer,
        //前两个参数同ack,第三个参数 为是否重回队列,true的话,broker会重新发送改消息给消费端
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
    }

}
```

测试:发送error消息,则进行无限循环,不停的打印消费失败.

实际上在异常中可以通过缓存设置重回队列的次数,超过次数进入死信队列/或者进行其它方式的暂存.

死信队列简单理解就是nack是不进行requeue,而是将死信队列绑定到死信交换机,而且可以利用死信队列进行延迟处理.


package com.zong.web;

import com.zong.utils.RedisUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;

@RestController

public class ProductController {
    @Resource
    private RedisUtils redisUtils;

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/buy")
    public String buy() {
        synchronized (this) {
            String s = redisUtils.get("stock").toString();

            Integer i = Integer.parseInt(s);
            if (i > 0) {
                int real = i - 1;
                redisUtils.set("stock", real + "");
                logger.info("购买成功,剩余:" + real);
            } else {
                logger.info("购买失败");
            }
        }
        return "buy end";
    }

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/buy2")
    public String buy2() {
        String lockKey = "product_01";
        Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, "zong");
        if (!result) {
            return "error";

        }
        Integer i = Integer.parseInt(redisUtils.get("stock").toString());
        if (i > 0) {
            int real = i - 1;
            redisUtils.set("stock", real + "");
            logger.info("购买成功,剩余:" + real);
        } else {
            logger.info("购买失败");
        }
        redisTemplate.delete(lockKey);
        return "buy end";
    }

    @Autowired
    Redisson redisson;

    @GetMapping("/buy3")
    public String buy3() {
        String lockKey = "product_01";
        RLock lock = redisson.getLock(lockKey);
        try {
            lock.lock();//真正的加锁 setIfAbsent
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
}

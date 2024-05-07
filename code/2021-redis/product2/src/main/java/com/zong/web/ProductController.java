package com.zong.web;

import com.zong.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController

public class ProductController {
    @Resource
    private RedisUtils redisUtils;

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/buy")
    public String buy() {
        String s = redisUtils.get("stock").toString();

        Integer i = Integer.parseInt(s);
        if (i > 0) {
            int real = i - 1;
            redisUtils.set("stock", real + "");
            logger.info("购买成功,剩余:" + real);
        } else {
            logger.info("购买失败");
        }

        return "buy end";
    }

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/buy2")
    public String buy2() {
        String lockKey = "product_01";
        String id  = UUID.randomUUID().toString();
        // setIfAbsent原子命令
        Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, id,10,TimeUnit.SECONDS);//同时设置过期时间
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
            if (id.equals(redisTemplate.opsForValue().get(lockKey))){
                redisTemplate.delete(lockKey);
            }
        }
        return "buy end";
    }
}

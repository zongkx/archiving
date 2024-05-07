package com.zong.web;

import com.zong.content.Content;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController


public class TestController {
    @GetMapping("/test")
    public String get() {
        return  "ss";
    }


    @GetMapping("/feign")
    public String get(String name) {
        System.out.println(name);
        return  "feign";
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/mq1")
    public String publish1(String ans){
        String msg = "hello world";
        rabbitTemplate.convertAndSend("hello","hello world");
        return msg;
    }

    @GetMapping("/mq2")
    public String publish2(String ans){
        rabbitTemplate.convertAndSend("work","work");
        return Thread.currentThread().getName();
    }


    @GetMapping("/mq3")
    public String publish3(String ans){
        //跟路由key没有关系,第二个参数为空
        rabbitTemplate.convertAndSend("logs","","Fanout模型发送的消息");
        return Thread.currentThread().getName();
    }

    @GetMapping("/mq4")
    public String publish4(String ans){
        rabbitTemplate.convertAndSend("direct","info",
                "发送info的key的exchange信息");
        rabbitTemplate.convertAndSend("direct","error",
                "发送error的key的exchange信息");
        return Thread.currentThread().getName();
    }

    @GetMapping("/mq5")
    public String publish5(String ans){
        rabbitTemplate.convertAndSend("topics","user.save", "user.save的路由消息");
        return Thread.currentThread().getName();
    }

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/mq6")
    public String publish6(String ans){
        try{


            rabbitTemplate.convertAndSend("direct", "ack", ans);

        }catch (Exception e){
            System.out.println(e.toString());
        }



        return Thread.currentThread().getName();
    }

    @GetMapping("/set")
    public void setMQ(){
        //            rabbitTemplate.setMandatory(true);//设置交换机处理失败消息的模式
//            RetryTemplate retryTemplate = new RetryTemplate();//用于设置重连次数
//            retryTemplate.setRetryPolicy(new SimpleRetryPolicy(2));
//            rabbitTemplate.setRetryTemplate(retryTemplate);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {//correlationData 相关配置新信息 cause:原因
                // 存缓存操作
                System.out.println("发送失败:" + cause);
                //次数也应该用缓存设置最高重发次数
                //进行处理,保证消息继续发送
            }else{
                System.out.println("success");
            }
        });
        // 确认是否发到队列，若没有则存缓存，然后检查exchange, routingKey配置，之后重发
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            Object o = redisTemplate.opsForValue().get("num");
            if(o == null){
                redisTemplate.opsForValue().set("num",0);
            }else{
                Integer i = Integer.parseInt(o.toString());
                i ++;
                if(i<3){
                    redisTemplate.opsForValue().set("num",i);
                    // 存缓存操作
                    System.out.println(new String(message.getBody()) + "找不到队列，exchange为" + exchange + ",routingKey为" + routingKey);
                    rabbitTemplate.convertAndSend("direct", "ack", "return");
                }else{
                    System.out.println("1111");
                }
            }
        });
    }
}

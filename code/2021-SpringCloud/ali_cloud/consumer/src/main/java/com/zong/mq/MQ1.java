package com.zong.mq;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello",
        durable = "true",autoDelete = "false",exclusive = "false"))
public class MQ1 {
    @RabbitHandler
    public void receive(String msg){
        System.out.println("这是直连模式消费者:"+msg);
    }
}

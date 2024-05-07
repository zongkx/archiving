package com.zong.mq;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component

public class MQ2 {

    @RabbitListener(queuesToDeclare = @Queue(value = "work" ))
    public void receive1(String msg){
        System.out.println("这是工作队列模式消费者1:"+msg);
    }


    @RabbitListener(queuesToDeclare = @Queue(value = "work" ))
    public void receive2(String msg){
        System.out.println("这是工作队列模式消费者2:"+msg);
    }
}

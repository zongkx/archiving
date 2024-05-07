package com.zong.mq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
public class MQ5 {

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
}

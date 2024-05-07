package com;

import com.zong.ConsumerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ConsumerApplication.class)
//@RunWith(SpringRunner.class)
public class TestRabbitMQ {

    private RabbitTemplate rabbitTemplate;

    //hello world
    @Test
    public void test(){
        rabbitTemplate.convertAndSend("hello","hello world");
    }
}

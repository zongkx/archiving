package com.zong.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MQ4 {

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


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "ackQueue") ,
                arguments = {@Argument(name = "x-dead-letter-exchange", value = "deadExchange"),
                        @Argument(name = "x-dead-letter-routing-key", value = "deadKey")
                , @Argument(name = "x-message-ttl",value = "10000",type = "java.lang.Integer")
                 ,@Argument(name = "x-max-length",value = "5",type = "java.lang.Integer") //队列最大长度
            },//绑定临时队列(没有加name属性)
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
            System.out.println("myQueue6:" + msg);
            //消息处理失败,调用nack,签收失败,broker重新发送给consumer,
            //前两个参数同ack,第三个参数 为是否重回队列,true的话,broker会重新发送改消息给消费端
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }

    }
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "deadQueue"),
                    exchange = @Exchange(value = "deadExchange"),
                    key = "deadKey"
            )
    })
    public void receive2(Message message, Channel channel) throws IOException {
        System.out.println("我是一条死信:"+message);
        long consumerTag = message.getMessageProperties().getDeliveryTag();
        //  channel.basicAck(consumerTag,true);
    }


}

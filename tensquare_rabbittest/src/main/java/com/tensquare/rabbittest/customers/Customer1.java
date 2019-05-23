package com.tensquare.rabbittest.customers;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue_name")
public class Customer1 {

    @RabbitHandler
    public void showMessage(String msg){
        System.out.println("queue_name消息内容："+msg);
    }




}

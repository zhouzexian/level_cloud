package com.joey.cloud.stream.service.impl;

import com.joey.cloud.stream.service.StreamClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author joey
 */
@Component
@EnableBinding(StreamClient.class)
public class StreamReceiver {
    @StreamListener(StreamClient.OUTPUT)
    @SendTo(StreamClient.INPUT)
    public String handleMessage(Object message){
        System.out.println("收到订单消息:");
        System.out.println(message.toString());
        return "receive ok";
    }

    @StreamListener(StreamClient.INPUT)
    public void confirmMessage(Object message){
        System.out.println("确认收到了订单消息:" + message.toString());
    }
}

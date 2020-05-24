package com.joey.cloud.stream.controller;

import com.joey.cloud.stream.service.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author joey
 */
@RestController
@RequestMapping("/stream")
public class StreamController {
    @Autowired
    private StreamClient streamClient;

    @PostMapping("/message")
    public String sendOrderMessage(){
        Map<String,Object> map = new HashMap<>(4);
        map.put("test","test");
        streamClient.out().send(MessageBuilder.withPayload(map).build());
        return "send order ok";
    }
}

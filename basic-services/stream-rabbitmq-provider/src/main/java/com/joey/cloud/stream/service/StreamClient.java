package com.joey.cloud.stream.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author joey
 */
public interface StreamClient {
    String INPUT = "myMessageIn";

    String OUTPUT = "myMessageOut";

    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    @Output(StreamClient.OUTPUT)
    MessageChannel out();
}

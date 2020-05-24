package com.joey.cloud.mq.receiver;


import com.joey.cloud.common.core.form.MessageForm;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 监听的队列名称 TestDirectQueue
 * @author joey
 */
@Component
@RabbitListener(queues = "TestDirectQueue")
public class DirectReceiver {
    @RabbitHandler
    public void process(MessageForm msg) {
        System.out.println("DirectReceiver消费者收到消息  : " + msg.toString());
    }
}

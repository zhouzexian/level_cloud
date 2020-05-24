package com.joey.cloud.mq.controller;

import com.joey.cloud.common.core.form.MessageForm;
import com.joey.cloud.common.core.utils.IdWorker;
import com.joey.cloud.common.core.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息发送者
 * @author joey
 */
@Api(tags = "1、RabbitMq消息中间价测试 API")
@ApiSort(value = 1)
@Slf4j
@RestController
public class SendMessageController {
    @Value("${joey.rabbitmq.exchange-key}")
    String exchangeKey;
    @Value("${joey.rabbitmq.routing-key}")
    String routingKey;
    @Resource
    RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "发送测试消息")
    @PostMapping("/message")
    public ResponseVo sendMessage(MessageForm form) {
        form.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        rabbitTemplate.convertAndSend(exchangeKey, routingKey, form);
        return ResponseVo.success();
    }

}

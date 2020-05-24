package com.joey.cloud.mq.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Rabbit配置类
 * @author joey
 */
@Configuration
public class RabbitConfig {
    @Value("${joey.rabbitmq.queue-name}")
    String queueName;
    @Value("${joey.rabbitmq.exchange-key}")
    String exchangeKey;
    @Value("${joey.rabbitmq.routing-key}")
    String routingKey;

    /**
     * 队列
     * @return
     */
    @Bean(value = "${joey.rabbitmq.queue-name}")
    public Queue initQueue() {
        /**
         * 1、durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
         * 2、exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
         * 3、autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
         * 4、一般设置一下队列的持久化就好,其余两个就是默认false
         */
        return new Queue(queueName,true);
    }
    /**
     * Direct交换机
     * @return
     */
    @Bean(value = "${joey.rabbitmq.exchange-key}")
    DirectExchange initExchange() {
        /**
         * 1、Direct Exchange 直连型交换机，根据路由键将消息投递给对应队列
         * 2、Fanout Exchange 扇型交换机，转发到绑定到它上面的所有队列
         * 3、Topic Exchange 主题交换机，根据路由键发送到对应绑定键规则的队列（*=用来表示一个必须的单词#=用来表示任意数量单词）
         * 4、Header Exchange 头交换机
         * 5、Default Exchange 默认交换机
         * 6、Dead Letter Exchange 死信交换机
         */
        return new DirectExchange(exchangeKey,true,false);
    }
    /**
     * 将队列和交换机绑定
     * @return
     */
    @Bean
    Binding bindingDirect(@Qualifier(value = "${joey.rabbitmq.queue-name}") Queue queue,
                          @Qualifier(value = "${joey.rabbitmq.exchange-key}") DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(routingKey);
    }

}

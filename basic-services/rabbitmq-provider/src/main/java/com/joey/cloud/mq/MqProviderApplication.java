package com.joey.cloud.mq;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author joey
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwaggerBootstrapUI
public class MqProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqProviderApplication.class, args);
    }
}

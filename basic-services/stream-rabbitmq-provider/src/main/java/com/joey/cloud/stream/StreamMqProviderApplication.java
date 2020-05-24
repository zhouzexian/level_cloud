package com.joey.cloud.stream;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author joey
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwaggerBootstrapUI
@EnableBinding
public class StreamMqProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamMqProviderApplication.class, args);
    }
}

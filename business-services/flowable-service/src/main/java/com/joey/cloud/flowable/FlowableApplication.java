package com.joey.cloud.flowable;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.joey.cloud.flowable.conf.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * @author joey
 */

@EnableDiscoveryClient
@EnableSwaggerBootstrapUI
@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class
})
@Import({ApplicationConfiguration.class, ApplicationConfiguration.class})
public class FlowableApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlowableApplication.class, args);
    }

}



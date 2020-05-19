package com.joey.cloud.psn;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author joey
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwaggerBootstrapUI
public class PsnApplication {
    public static void main(String[] args) {
        SpringApplication.run(PsnApplication.class, args);
    }
}

package com.joey.cloud.gatewayprj.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * swagger文档资源配置类 （重点swagger整合zuul配置）
 * @author joey
 */
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    @Resource
    ZuulProperties properties;

    private SwaggerResource createResource(ZuulProperties.ZuulRoute route) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(route.getServiceId());
        swaggerResource.setLocation(route.getPath().replace("/**","")+"/v2/api-docs");
        swaggerResource.setSwaggerVersion("1.0");
        return swaggerResource;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        Map<String, ZuulProperties.ZuulRoute> routes = properties.getRoutes();
        properties.getRoutes().values().stream()
                .forEach(route -> resources
                        .add(createResource(route)));
        return resources;
    }
}

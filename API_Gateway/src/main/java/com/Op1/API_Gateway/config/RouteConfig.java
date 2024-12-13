package com.Op1.API_Gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
            .route("product_service", r -> r.path("/api/products/**").uri("lb://product_service"))
            .route("order_service", r -> r.path("/api/orders/**").uri("lb://order_service"))
            .build();

    }
}

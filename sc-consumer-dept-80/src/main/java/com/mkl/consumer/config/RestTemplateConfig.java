package com.mkl.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    // 配置负载均衡实现RestTemplate
    @LoadBalanced  // Ribbon
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

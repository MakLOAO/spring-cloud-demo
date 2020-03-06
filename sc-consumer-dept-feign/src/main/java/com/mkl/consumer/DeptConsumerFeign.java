package com.mkl.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.mkl.consumer", "com.mkl.api"})
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.mkl.api"}) // 这里指定的是封装类的包路径
public class DeptConsumerFeign {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerFeign.class, args);
    }
}

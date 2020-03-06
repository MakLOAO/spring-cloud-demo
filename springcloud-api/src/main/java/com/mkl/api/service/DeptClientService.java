package com.mkl.api.service;

import com.mkl.api.pojo.Dept;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 使用feign调用服务
// 服务降级：fallbackFactory指定失败时使用客户端使用哪个工厂来创建类处理请求
@FeignClient(value = "SC-PROVIDER-DEPT", fallbackFactory = DeptClientServiceFallbackFactory.class) // value是微服务的名字
@Service
public interface DeptClientService {

    @GetMapping("/dept/get/{id}")
    Dept queryById(@PathVariable("id") Long id);

    @GetMapping("/dept/list")
    List<Dept> queryAll();

    @PostMapping("/dept/add")
    Boolean addDept(Dept dept);
}

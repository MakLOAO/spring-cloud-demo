package com.mkl.consumer.controller;

import com.mkl.api.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {

    // 80端口被占用了，所以先用着8004

    @Autowired
    RestTemplate restTemplate;

    // ribbon实现的时候，这里的地址应该是一个变量，通过服务名来访问
//    private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX = "http://SC-PROVIDER-DEPT";

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept) {
        // 通过POST请求去远程调用，这里可以用 http://localhost:8003/consumer/dept/add?dname='233' 来调用
        // 因为这里会把GET请求的参数封装到dept，再用这个封装的dept用POST请求调用远程服务
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(Dept dept) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }

    @GetMapping("/consumer/discovery")
    public Object discovery() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
    }

}

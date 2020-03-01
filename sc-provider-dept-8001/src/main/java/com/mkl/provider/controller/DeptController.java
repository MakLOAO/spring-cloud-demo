package com.mkl.provider.controller;

import com.mkl.api.pojo.Dept;
import com.mkl.provider.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/discovery")
    public Object discovery() {
        // 获取微服务列表清单
        List<String> services = discoveryClient.getServices();
        for (String s : services)
            System.out.println("discovery->service:" + s);

        // 通过具体的微服务ID，applicationName，得到一个具体的微服务信息
        List<ServiceInstance> instances = discoveryClient.getInstances("SC-PROVIDER-DEPT-8001");

        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost() + "\t"
                    + instance.getPort() + "\t"
                    + instance.getUri() + "\t"
                    + instance.getServiceId()
            );
        }
        return this.discoveryClient;
    }

    @PostMapping("/dept/add")
    public boolean addDept(@RequestBody Dept dept) {
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return deptService.queryById(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> addDept() {
        return deptService.queryAll();
    }
}

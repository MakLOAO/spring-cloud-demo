package com.mkl.provider.controller;

import com.mkl.api.pojo.Dept;
import com.mkl.provider.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = deptService.queryById(id);
        if (dept == null) {
            throw new RuntimeException("id -> " + id + "，不存在该用户");
        }
        return deptService.queryById(id);
    }

    // 备选方案
    public Dept hystrixGet(@PathVariable("id") Long id) {
        return new Dept()
                .setDeptNo(id)
                .setDname("id -> " + id + "，没有对应的信息，null@Hystrix")
                .setDbSource("no this database in MySQL");
    }

}

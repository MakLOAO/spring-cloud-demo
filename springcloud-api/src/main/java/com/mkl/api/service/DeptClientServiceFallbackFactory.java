package com.mkl.api.service;

import com.mkl.api.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

// 服务降级，服务调用失败时使用返回的这个service来处理请求
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {

    // （因性能问题）关闭服务器后，请求服务调用失败，则返回这个类来处理请求
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Dept queryById(Long id) {
                return new Dept()
                        .setDeptNo(id)
                        .setDname(id + "：没有对应的信息，客户端提供了降级的信息，这个服务现在已经被关闭了")
                        .setDbSource("no database");
            }

            @Override
            public List<Dept> queryAll() {
                // 省略
                return null;
            }

            @Override
            public Boolean addDept(Dept dept) {
                // 省略
                return null;
            }
        };
    }
}

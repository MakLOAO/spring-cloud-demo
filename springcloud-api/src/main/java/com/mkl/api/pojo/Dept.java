package com.mkl.api.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true) // 链式写法
// 链式写法可以这样写： dept.setDept_no(11).setDname("aa").setXxx()...
public class Dept implements Serializable {

    private Long deptNo;
    private String dname;
    // 该数据存储在哪个数据库
    private String dbSource;

    public Dept(String dname) {
        this.dname = dname;
    }
}

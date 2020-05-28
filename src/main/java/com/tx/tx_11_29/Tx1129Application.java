package com.tx.tx_11_29;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan(basePackages = {"com.tx.tx_11_29.mapper"})
//@EnableAspectJAutoProxy(exposeProxy = true)
public class Tx1129Application {


    public static void main(String[] args) {

        SpringApplication.run(Tx1129Application.class, args);
    }

}

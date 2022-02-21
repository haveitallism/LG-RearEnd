package com.group8;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.group8.dao")
public class LgOrder8300Application {
    public static void main(String[] args) {
        SpringApplication.run(LgOrder8300Application.class, args);
    }
}

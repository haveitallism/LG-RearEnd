package com.group8;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.group8.dao")
public class CommentsApplication8200 {
    public static void main(String[] args) {
        SpringApplication.run(CommentsApplication8200.class,args);
    }
}

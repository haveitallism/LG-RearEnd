package com.group8;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * description: OrderApplication8400 <br>
 * date: 2022/2/17 10:46 上午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
@SpringBootApplication
@MapperScan("com.group8.dao")
public class GroupApplication8400 {
    public static void main(String[] args) {
        SpringApplication.run(GroupApplication8400.class, args);
    }
}

package com.group8;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author laiyong
 * @date 2022/2/17 11:11 星期四
 * @apiNote
 */
@SpringBootApplication
@MapperScan("com.group8.dao")
public class LgUser8100Application {
    public static void main(String[] args) {
        SpringApplication.run(LgUser8100Application.class, args);
    }
}

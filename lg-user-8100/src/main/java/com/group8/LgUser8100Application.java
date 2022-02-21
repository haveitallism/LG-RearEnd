package com.group8;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author laiyong
 * @date 2022/2/17 11:11 星期四
 * @apiNote
 */
@SpringBootApplication
@EnableRabbit
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.group8.dao")
public class LgUser8100Application {
    public static void main(String[] args) {
        SpringApplication.run(LgUser8100Application.class, args);
    }
}

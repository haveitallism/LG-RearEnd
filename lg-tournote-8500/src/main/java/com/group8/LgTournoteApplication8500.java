package com.group8;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author acoffee
 * @create 2022-02-17 13:01
 */
@SpringBootApplication
@MapperScan("com.group8.dao")
@EnableDiscoveryClient
public class LgTournoteApplication8500 {
    public static void main(String[] args) {
        SpringApplication.run(LgTournoteApplication8500.class,args);
    }
}

package com.group8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/*
 * @author laiyong
 * @date 2022/2/21 15:57 星期一
 * @apiNote
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LgGateway8000Application {
    public static void main(String[] args) {
        SpringApplication.run(LgGateway8000Application.class, args);
    }
}

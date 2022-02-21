package com.group8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author QY
 * @create 2022-02-17 14:29
 */

@SpringBootApplication
@EnableDiscoveryClient
public class EmailApplication8101 {
    public static void main(String[] args) {
        SpringApplication.run(EmailApplication8101.class,args);
    }
}

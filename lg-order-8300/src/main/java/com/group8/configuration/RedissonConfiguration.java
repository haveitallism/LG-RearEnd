package com.group8.configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfiguration {
    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://162.14.112.78:6379");
        config.useSingleServer().setDatabase(4);
        config.useSingleServer().setPassword("123");
        return Redisson.create(config);
    }
}

package com.group8.configuration;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author QY
 * @create 2022-02-17 15:02
 */

@Configuration
public class RabbitMqConfig {

    //配置类中添加JSON消息转化器，底层默认使用的Jackson
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}

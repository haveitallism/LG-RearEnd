package com.group8.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfiguration {

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange exchangeadd(){
        return new TopicExchange("exchangeadd");
    }

    @Bean
    public Queue queueadd(){
        return new Queue("queueadd");
    }

    @Bean
    public Binding bindingqueueadd(TopicExchange exchangeadd,Queue queueadd){
        return BindingBuilder.bind(queueadd).to(exchangeadd).with("#.add.#");
    }
}

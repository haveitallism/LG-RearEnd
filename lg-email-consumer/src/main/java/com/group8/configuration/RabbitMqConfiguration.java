package com.group8.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author QY
 * @create 2022-02-17 14：19
 */

@Configuration
public class RabbitMqConfiguration {

    /**
     * 转换JSON器
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 定义交换机
     * @return
     */
    @Bean
    public TopicExchange exchangeMail(){
        TopicExchange exchangeMail = new TopicExchange("LG-mail-exchange");
        return  exchangeMail;
    }

    /**
     * 定义注册普通用户发送邮件激活渠道
     * @return
     */
    @Bean
    public Queue queueMail(){
        return new Queue("LgMail");
    }

    /**
     * 定义注册商铺用户发送邮件激活渠道
     * @return
     */
    @Bean
    public Queue queueBuMail(){
        return new Queue("LgBuMail");
    }

    @Bean
    public Binding bindingQueueDelay(Queue queueMail, TopicExchange exchangeMail){
        return BindingBuilder.bind(queueMail).to(exchangeMail).with("#.LgMail.#");
    }

    @Bean
    public Binding bindingQueueDelay1(Queue queueBuMail, TopicExchange exchangeMail){
        return BindingBuilder.bind(queueBuMail).to(exchangeMail).with("#.LgMail1.#");
    }








}

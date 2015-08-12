package com.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * Created by dqf on 2015/8/12.
 */
@Configuration
public class ApplicationConfig {
    @Bean
    public Queue queue(){
        return new ActiveMQQueue("sample.queue");
    }

    @Bean
    public SecurityProperties securityProperties(){
        SecurityProperties security = new SecurityProperties();

        security.getBasic().setPath("");     // empty so home page is unsecured
        return security;
    }
}

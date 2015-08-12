package com;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * Created by dqf on 2015/8/12.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableJms
public class Application extends SpringBootServletInitializer{
    @Bean
    public Queue queue(){
        return new ActiveMQQueue("sample.queue");
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class);
        System.out.println("Application Running...");
    }

    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application){
        return application.sources(Application.class);
    }
}

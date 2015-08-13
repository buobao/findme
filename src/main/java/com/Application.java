package com;

import com.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

/**
 * Created by dqf on 2015/8/12.
 */
//@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableJms
public class Application extends SpringBootServletInitializer /*implements CommandLineRunner*/ {
    @Autowired
    private HelloWorldService helloWorldService;

//    @Override
//    public void run(String... args) {
//        System.out.println(this.helloWorldService.getHelloMessage());
//    }

    public static void main(String[] args){
        SpringApplication.run(Application.class);
        System.out.println("Application Running...");

    }

    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application){
        return application.sources(Application.class);
    }
}

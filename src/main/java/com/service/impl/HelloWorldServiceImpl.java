package com.service.impl;

import com.service.HelloWorldService;
import org.springframework.stereotype.Service;

/**
 * Created by dqf on 2015/8/12.
 */
@Service
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String getHelloMessage() {
        return "Hello,Buobao!";
    }
}

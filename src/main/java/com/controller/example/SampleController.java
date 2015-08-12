package com.controller.example;

import com.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

/**
 * Created by dqf on 2015/8/12.
 * 请求返回json数据
 */
@Controller
public class SampleController {
    @Autowired
    private HelloWorldService helloWorldService;

    @RequestMapping("/hello")
    @ResponseBody
    public Map<String, String> helloWorld(){
        return Collections.singletonMap("message",helloWorldService.getHelloMessage());
    }

    @RequestMapping("/foo")
    @ResponseBody
    public String foo() {
        throw new IllegalArgumentException("Server error");
    }
}

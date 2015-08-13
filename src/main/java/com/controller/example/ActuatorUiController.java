package com.controller.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

/**
 * Created by dqf on 2015/8/12.
 * 使用jsp页面模版
 */
@Controller
public class ActuatorUiController {
    @RequestMapping("/home")
    public String home(Map<String,Object> model){
        model.put("message","This is my house!");
        model.put("title","Wade");
        model.put("date",new Date());
        return "home";
    }

    @RequestMapping("/error_page")
    public String error(Map<String,Object> model){
        model.put("message","This is a Error Page");
        model.put("timestamp",new Date());
        model.put("status","404");
        model.put("error","Test Error");
        return "error";
    }
}

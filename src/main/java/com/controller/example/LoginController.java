package com.controller.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

/**
 * Created by dqf on 2015/8/13.
 */
@Controller
public class LoginController {
    private String message = "Welcome Login";

    @RequestMapping("/login")
    public String login(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        return "login";
    }
}

package com.controller.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dqf on 2015/8/12.
 * 通过返回字串显示jsp页面
 */
@Controller
public class RestfulController {
    @ModelAttribute("users")
    String getUserList() {
        return "String users";
    }

    @RequestMapping("/restful.html")
    public String getListUsersView() {
        return "message";
    }
}

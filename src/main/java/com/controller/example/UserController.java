package com.controller.example;

import com.entity.User;
import com.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Created by dqf on 2015/8/12.
 * 通过ModelAndView显示jsp页面
 */
@Controller
public class UserController {
    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/user",method = RequestMethod.POST)
    public User createUser(@RequestBody @Valid final User user){
        return userService.save(user);
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/user_list.html")
    public ModelAndView getListUsersView(){
        ModelMap model = new ModelMap();
        model.addAttribute("name","buobao");
        //model.addAttribute("users",userService);
        return new ModelAndView("user_list",model);
    }

    @RequestMapping("/foo")
    @ResponseBody
    public String foo() {
        throw new IllegalArgumentException("Server error");
    }
}
















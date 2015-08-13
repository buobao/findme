package com.controller.example;

import com.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by dqf on 2015/8/12.
 * 通过返回字串显示jsp页面
 */
@Controller
@RequestMapping("/public")
public class RestfulController {
    @Value("${test.msg:somthing}")           //通过这个标记可以读取配置文件中的信息
    private String msg;
    private String userId;

    @ModelAttribute("msg")
    String getMsg(){
        return msg;
    }

    @ModelAttribute("users")
    String getUserList() {
        return "String users";
    }

    @ModelAttribute("userId")
    String getUserId(){
        return userId;
    }

    @RequestMapping("/restful.html")
    public String getListUsersView() {
        Jedis jedis = JedisUtil.getJedis();
        jedis.set("springBootTest","This is redis test string!");
        System.out.println("Get from Redis: value->"+jedis.get("springBootTest"));
        JedisUtil.returnJedis(jedis);
        jedis = JedisUtil.getJedis();
        jedis.set("springBootTest","This is redis test string2!");
        System.out.println("Get from Redis: value->"+jedis.get("springBootTest"));
        JedisUtil.returnJedis(jedis);
        return "message";
    }

    //test for session
    @RequestMapping("/session")
    public String session(HttpSession session){
        UUID uid = (UUID)session.getAttribute("uid");
        if (uid == null){
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid",uid);
        userId = uid.toString();
        return "session";
    }

}

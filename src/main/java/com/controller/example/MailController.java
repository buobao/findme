package com.controller.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by dqf on 2015/8/14.
 */
@Controller
public class MailController {
    private final JavaMailSender javaMailSender;
    private String msg;

    @ModelAttribute("msg")
    String getMsg(){
        return msg;
    }

    @Autowired
    MailController(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @RequestMapping("/mail")
    //@ResponseStatus(HttpStatus.CREATED)
    public String mail(){
        msg="";
        return "mail";
    }

    @RequestMapping("/sendMail")
    public String send(@RequestParam Map<String,Object> data, HttpServletRequest request){
//        for (String key : data.keySet()) {
//            System.out.println("key= "+ key + " and value= " + data.get(key));
//        }
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(data.get("mailAddress").toString());
        mailMessage.setReplyTo(data.get("replayAddress").toString());
        mailMessage.setFrom(data.get("fromAddress").toString());
        mailMessage.setSubject(data.get("subject").toString());
        mailMessage.setText(data.get("message").toString());
        javaMailSender.send(mailMessage);


        msg = "Send Success!";
        return "mail";
    }

}

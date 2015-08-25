package com.service.common;

import com.bean.Email;
import com.utils.LogUtil;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by dqf on 2015/8/24.
 */
@Service
public class SimpleMailService {
    private static Logger logger = LoggerFactory.logger(SimpleMailService.class);
    private JavaMailSender mailSender;
    private String textTemplate;

    public void SimpleMailService(){}

    public void sendNotificationMail(String userName){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("工程泛联客<projectManager@9joint.com.cn>");
        msg.setTo("xuminwlt2008@163.com");
        msg.setSubject("用户注册邮箱验证");
        String url = "http://cst.9joint-eco.com";
        String content = String.format(this.textTemplate, new Object[]{userName, new Date(),url});
        msg.setText(content);
    }

    public void sendRegisterAuthCodeMail(String username, String authcode){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("工程泛联客<projectManager@9joint.com.cn>");
        msg.setTo(username);
        msg.setSubject("用户注册邮箱验证");
        msg.setText("验证码："+authcode);
        try{this.mailSender.send(msg);}catch (Exception e){}
    }

    public void send(Email email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("工程泛联客<projectManager@9joint.com.cn>");
        msg.setTo(email.getAccount());
        msg.setSubject(email.getSubject());
        msg.setText(email.getBody());

        try {
            this.mailSender.send(msg);
            LogUtil.info("纯文本邮件已发送至{}" + email.getAccount());
        } catch (Exception var4) {
            logger.error("发送邮件失败", var4);
        }

    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTextTemplate(String textTemplate) {
        this.textTemplate = textTemplate;
    }

}































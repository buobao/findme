package com.bean;

import java.io.Serializable;

/**
 * Created by dqf on 2015/7/14.
 */
public class Email implements Serializable {
    private static final long serialVersionUID = -2755329278196422648L;
    private String body;
    private String account;
    private String subject;

    public Email(){}

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

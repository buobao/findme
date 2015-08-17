package com.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dqf on 2015/7/14.
 */
public class Result {
    private int state;
    private String message;
    private Map<String, Object> data;
    private long errorCode;

    public Result(){}

    public Result(int state, String message){
        this(state, message, new HashMap());
    }

    public Result(int state, String message, Map<String, Object> data){
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public Result(int state, String message, long errorCode, Map<String, Object> data) {
        this.state = state;
        this.message = message;
        this.errorCode = errorCode;
        this.data = data;
    }

    public String getId(){
        return this.getData() != null && this.getData().containsKey("id")?(String)this.getData().get("id"):null;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }
}

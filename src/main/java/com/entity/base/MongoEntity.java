package com.entity.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dqf on 2015/8/17.
 */
public class MongoEntity implements Serializable {
    private static final long serialVersionUID = -2755329278196422648L;
    protected String id;
    protected Date createDate;
    protected Date modifyDate;
    protected String timestamp;

    public MongoEntity() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return this.modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

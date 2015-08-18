package com.entity.core;

import com.entity.base.NameEntity;
import com.entity.sys.Users;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by dqf on 2015/8/18.
 */
@Entity
@Table(name="ec_announce")
public class Announce extends NameEntity {
    /**
     * 发布时间
     */
    private Date publishTime;
    /**
     * 公告内容
     */
    private String content;

    /**
     * 通知方式 全员0，部分人员1
     */
    private int callType;
    /**
     * 通知人员
     */
    private Set<Users> usersSet;

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="ec_announce_users",joinColumns = @JoinColumn(name="id"),inverseJoinColumns = @JoinColumn(name="userId"))
    @OrderBy("id asc")
    public Set<Users> getUsersSet() {
        return usersSet;
    }

    public void setUsersSet(Set<Users> usersSet) {
        this.usersSet = usersSet;
    }

    public int getCallType() {
        return callType;
    }

    public void setCallType(int callType) {
        this.callType = callType;
    }
}

package com.entity.core;

import com.entity.base.BaseEntity;
import com.entity.sys.Users;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dqf on 2015/8/18.
 */
@Entity
@Table(name="ec_proInfoLog")
public class ProInfoLog extends BaseEntity {
    /**
     * 项目信息
     */
    private ProInfo proInfo;
    /**
     * 原总监
     */
    private Users oldchief;
    /**
     * 原项目组成员
     */
    private Set<Users> oldgroup;

    /**
     * 现总监
     */
    private Users newchief;
    /**
     * 现项目组成员
     */
    private Set<Users> newgroup;

    @ManyToOne(fetch = FetchType.LAZY)
    public ProInfo getProInfo() {
        return proInfo;
    }

    public void setProInfo(ProInfo proInfo) {
        this.proInfo = proInfo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Users getOldchief() {
        return oldchief;
    }

    public void setOldchief(Users oldchief) {
        this.oldchief = oldchief;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="ec_proInfoLog_oldgroup",joinColumns = @JoinColumn(name="id"),inverseJoinColumns = @JoinColumn(name="userId"))
    @OrderBy("name asc")
    public Set<Users> getOldgroup() {
        return oldgroup;
    }

    public void setOldgroup(Set<Users> oldgroup) {
        this.oldgroup = oldgroup;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Users getNewchief() {
        return newchief;
    }

    public void setNewchief(Users newchief) {
        this.newchief = newchief;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="ec_proInfoLog_newgroup",joinColumns = @JoinColumn(name="id"),inverseJoinColumns = @JoinColumn(name="userId"))
    @OrderBy("name asc")
    public Set<Users> getNewgroup() {
        return newgroup;
    }

    public void setNewgroup(Set<Users> newgroup) {
        this.newgroup = newgroup;
    }


}

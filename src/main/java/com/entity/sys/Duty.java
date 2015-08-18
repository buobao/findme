package com.entity.sys;

import com.bean.EnumManage;
import com.entity.base.BaseEntity;

import javax.persistence.*;

/**
 * Created by dqf on 2015/8/17.
 */
@Entity
@Table(name="sys_duty")
public class Duty extends BaseEntity {
    private static final long serialVersionUID = 13320517262715056L;

    public Duty() {
        super();
    }

    public Duty(Users users, Power power) {
        super();
        this.users = users;
        this.power = power;
    }
    public Duty(Users users, Power power,Department department,Post post) {
        super();
        this.users = users;
        this.power = power;
        this.department = department;
        this.post = post;
    }

    public Duty(Users users, Power power, EnumManage.DutyState dutyState) {
        super();
        this.users = users;
        this.power = power;
        this.dutyState = dutyState;
    }
    /**
     *用户
     */
    private Users users;
    /**
     *职权
     */
    private Power power;
    /**
     * 部门
     */
    private Department department;
    /**
     * 岗位
     */
    private Post post;

    /**
     *职责情况
     */
    private EnumManage.DutyState dutyState;

    /**
     * 是否默认岗位，同一个人的职责类，只存在一个默认1,否则0
     * */
    private int dutyDefault;


    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="users_id")
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Enumerated(EnumType.STRING)
    public EnumManage.DutyState getDutyState() {
        return dutyState;
    }

    public void setDutyState(EnumManage.DutyState dutyState) {
        this.dutyState = dutyState;
    }

    public int getDutyDefault() {
        return dutyDefault;
    }

    public void setDutyDefault(int dutyDefault) {
        this.dutyDefault = dutyDefault;
    }


}

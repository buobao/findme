package com.entity.core;

import com.bean.EstEnum;
import com.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by dqf on 2015/8/18.
 */
@Entity
@Table(name="ec_adminInfo")
public class AdminInfo extends BaseEntity{
    /**
     * authCode
     */
    private String authCode;
    /**
     * 随机数
     */
    private String random;
    /**
     * 注册人的手机
     */
    private String mobile;

    /**
     * 时间戳
     */
    private String annce;
    /**
     * 剩余天数
     */
    private int days;

    /**
     * adminId
     */
    private String adminId;
    /**
     * 所属企业
     */
    //private String companyId;
    /**
     * 成员状态
     */
    private EstEnum.AdminStatusEnum status;
    /**
     * 绑定时间
     */
    private Date bindDate;

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Enumerated(EnumType.STRING)
    public EstEnum.AdminStatusEnum getStatus() {
        return status;
    }

    public void setStatus(EstEnum.AdminStatusEnum status) {
        this.status = status;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAnnce() {
        return annce;
    }

    public void setAnnce(String annce) {
        this.annce = annce;
    }

//    public String getCompanyId() {
//        return companyId;
//    }
//
//    public void setCompanyId(String companyId) {
//        this.companyId = companyId;
//    }

    public Date getBindDate() {
        return bindDate;
    }

    public void setBindDate(Date bindDate) {
        this.bindDate = bindDate;
    }
}

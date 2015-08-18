package com.entity.core;

import com.entity.base.NameEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by dqf on 2015/8/18.
 */
@Entity
@Table(name="ec_proSignIn")
public class ProSignIn extends NameEntity {

    /**
     * 项目信息
     */
    private ProInfo proInfo;
    /**
     * 签到地址
     */
    private String address;
    /**
     * 签到对应的locate
     */
    private String locationId;
    /**
     * 考勤状态 (正常/迟到 --- 1/0)
     */
    private int status;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public ProInfo getProInfo() {
        return proInfo;
    }

    public void setProInfo(ProInfo proInfo) {
        this.proInfo = proInfo;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}

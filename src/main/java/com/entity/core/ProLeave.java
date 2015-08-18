package com.entity.core;

import com.entity.base.NameEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by dqf on 2015/8/18.
 */
@Entity
@Table(name="ec_proLeave")
public class ProLeave extends NameEntity {

    /**
     * 项目信息
     */
    private ProInfo proInfo;
    /**
     * 请假事由
     */
    private String reason;
    /**
     * 请假日期
     */
    private Date leaveDate;

    @ManyToOne(fetch = FetchType.LAZY)
    public ProInfo getProInfo() {
        return proInfo;
    }

    public void setProInfo(ProInfo proInfo) {
        this.proInfo = proInfo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }
}

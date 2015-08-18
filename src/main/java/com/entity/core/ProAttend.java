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
@Table(name="ec_proAttend")
public class ProAttend extends NameEntity {

    /**
     * 项目信息
     */
    private ProInfo proInfo;
    /**
     * 正常考勤开始时间
     */
    private Date regularStart;
    /**
     * 正常考勤结束时间
     */
    private Date regularEnd;
    /**
     * 异常考勤开始时间
     */
    private Date unRegularStart;
    /**
     * 异常考勤结束时间
     */
    private Date unRegularEnd;
    /**
     * 备注
     */
    private String mark;

    public Date getRegularStart() {
        return regularStart;
    }

    public void setRegularStart(Date regularStart) {
        this.regularStart = regularStart;
    }

    public Date getRegularEnd() {
        return regularEnd;
    }

    public void setRegularEnd(Date regularEnd) {
        this.regularEnd = regularEnd;
    }

    public Date getUnRegularStart() {
        return unRegularStart;
    }

    public void setUnRegularStart(Date unRegularStart) {
        this.unRegularStart = unRegularStart;
    }

    public Date getUnRegularEnd() {
        return unRegularEnd;
    }

    public void setUnRegularEnd(Date unRegularEnd) {
        this.unRegularEnd = unRegularEnd;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public ProInfo getProInfo() {
        return proInfo;
    }

    public void setProInfo(ProInfo proInfo) {
        this.proInfo = proInfo;
    }

}

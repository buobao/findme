package com.entity.core;

import com.entity.base.NameEntity;
import com.entity.sys.Dict;
import com.entity.sys.FileManage;
import com.entity.sys.Users;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by dqf on 2015/8/18.
 */
@Entity
@Table(name="ec_proBack")
public class ProBack extends NameEntity {
    /**
     * 项目名称
     */
    private ProInfo proInfo;

    /**
     * 反馈类别
     */
    private Dict category;

    /**
     * 反馈日期
     */
    private Date backDate;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 备注
     */
    private String mark;

    /**
     * 是否正常(1/0)
     */
    private int regular;

    /**
     * 反馈地址
     */
    private String address;

    /**
     * 反馈对应的locate
     */
    private String locationId;

    /**
     * 通知人员
     */
    private Set<Users> notice;
    /**
     * 图片路径
     * */
    private Set<FileManage> picsUrl;
    /**
     * 微信服务器返回的MediaIds
     */
    private String wxMediaIds;
    /**
     * 图片数量
     * */
    private int picAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    public ProInfo getProInfo() {
        return proInfo;
    }

    public void setProInfo(ProInfo proInfo) {
        this.proInfo = proInfo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Dict getCategory() {
        return category;
    }

    public void setCategory(Dict category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getRegular() {
        return regular;
    }

    public void setRegular(int regular) {
        this.regular = regular;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="ec_proBack_notice",joinColumns = @JoinColumn(name="id"),inverseJoinColumns = @JoinColumn(name="userId"))
    @OrderBy("name asc")
    public Set<Users> getNotice() {
        return notice;
    }

    public void setNotice(Set<Users> notice) {
        this.notice = notice;
    }

    public Date getBackDate() {
        return backDate;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @OrderBy("createDate desc")
    public Set<FileManage> getPicsUrl() {
        return picsUrl;
    }

    public void setPicsUrl(Set<FileManage> picsUrl) {
        this.picsUrl = picsUrl;
    }

    @Column(length = 65535)
    public String getWxMediaIds() {
        return wxMediaIds;
    }

    public void setWxMediaIds(String wxMediaIds) {
        this.wxMediaIds = wxMediaIds;
    }

    public int getPicAmount() {
        return picAmount;
    }

    public void setPicAmount(int picAmount) {
        this.picAmount = picAmount;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}


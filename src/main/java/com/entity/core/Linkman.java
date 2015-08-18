package com.entity.core;

import com.entity.base.NameEntity;
import com.utils.excel.annotation.ExcelField;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by dqf on 2015/8/18.
 */
@Entity
@Table(name="ec_linkman")
public class Linkman extends NameEntity {

    private static final long serialVersionUID = -7894524049778924812L;

    public Linkman(){
        super();
    }
    public Linkman(Client client, String name){
        super();
        this.setClient(client);
        this.setName(name);
    }
    /**
     * 客户
     */
    private Client client;
    /**
     * 客户常用称呼
     */
    private String subName;
    /**
     * 职位
     */
    private String duty;
    /**
     * 办公电话
     */
    private String tel;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 备用手机
     */
    private String mobile_back;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 个人喜好
     */
    private String preferences;
    /**
     * 是否有决策权 0/1
     */
    private String ifHasDecision;
    /**
     * 生日
     */
    private Date birthday;
    private int birthdayMonth;
    private int birthdayDay;
    /**
     * 备注
     */
    private String remark;

    /**
     *照片
     */
    private String fileId;

    @ExcelField(title="手机", align=2, sort=40)
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    @ExcelField(title="备注", align=2, sort=70)
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    @ExcelField(title="职位", align=2, sort=20)
    public String getDuty() {
        return duty;
    }
    public void setDuty(String duty) {
        this.duty = duty;
    }
    @ExcelField(title="办公电话", align=2, sort=30)
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    @ExcelField(title="邮箱", align=2, sort=60)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @ExcelField(title="个人喜好", align=2, sort=20)
    public String getPreferences() {
        return preferences;
    }
    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
    public String getIfHasDecision() {
        return ifHasDecision;
    }
    public void setIfHasDecision(String ifHasDecision) {
        this.ifHasDecision = ifHasDecision;
    }
    @ExcelField(title="客户名称", align=2, sort=0,value ="client.name")
    @ManyToOne(fetch = FetchType.LAZY)
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    @ExcelField(title="备用手机", align=2, sort=50)
    public String getMobile_back() {
        return mobile_back;
    }
    public void setMobile_back(String mobile_back) {
        this.mobile_back = mobile_back;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public int getBirthdayMonth() {
        return birthdayMonth;
    }
    public void setBirthdayMonth(int birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }
    public int getBirthdayDay() {
        return birthdayDay;
    }
    public void setBirthdayDay(int birthdayDay) {
        this.birthdayDay = birthdayDay;
    }

    @ExcelField(title="常用名称", align=2, sort=10)
    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}

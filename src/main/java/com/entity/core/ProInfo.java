package com.entity.core;

import com.entity.base.NameEntity;
import com.entity.sys.Dict;
import com.entity.sys.Users;
import com.utils.excel.annotation.ExcelField;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by dqf on 2015/8/18.
 */
@Entity
@Table(name="ec_proInfo")
public class ProInfo extends NameEntity {

    /**
     * 项目类别
     */
    private Dict category;
    /**
     * 客户
     */
    private Client client;
    /**
     * 联系人
     */
    private Linkman linkman;

    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String county;
    /**
     * 地址
     */
    private String address;
    /**
     * 地址（经纬度）
     */
    private String locationId;
    /**
     * 总监
     */
    private Users chief;
    /**
     * 项目组成员
     */
    private Set<Users> group;

    /**
     * 启动时间
     */
    private Date start;

    /**
     * 结束时间
     */
    private Date end;
    /**
     * 0(未完成)，1已完成
     */
    private int proState;

    @ExcelField(title="项目类别", align=2, sort=10,value ="category.name")
    @ManyToOne(fetch = FetchType.LAZY)
    public Dict getCategory() {
        return category;
    }

    public void setCategory(Dict category) {
        this.category = category;
    }

    @ExcelField(title="客户名称", align=2, sort=30,value ="client.name")
    @ManyToOne(fetch = FetchType.LAZY)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ExcelField(title="客户联系人", align=2, sort=40,value ="linkman.name")
    @ManyToOne(fetch = FetchType.LAZY)
    public Linkman getLinkman() {
        return linkman;
    }

    public void setLinkman(Linkman linkman) {
        this.linkman = linkman;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @ExcelField(title="项目地址", align=2, sort=50)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @ExcelField(title="项目总监", align=2, sort=20,value ="chief.name")
    @ManyToOne(fetch = FetchType.LAZY)
    public Users getChief() {
        return chief;
    }

    public void setChief(Users chief) {
        this.chief = chief;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="ec_proInfo_group",joinColumns = @JoinColumn(name="id"),inverseJoinColumns = @JoinColumn(name="userId"))
    @OrderBy("name asc")
    public Set<Users> getGroup() {
        return group;
    }

    public void setGroup(Set<Users> group) {
        this.group = group;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public int getProState() {
        return proState;
    }

    public void setProState(int proState) {
        this.proState = proState;
    }
}

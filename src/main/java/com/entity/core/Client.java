package com.entity.core;

import com.entity.base.NameEntity;
import com.entity.sys.Dict;
import com.utils.PinyinUtil;
import com.utils.excel.annotation.ExcelField;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dqf on 2015/8/18.
 */
@Entity
@Table(name="ec_client")
public class Client extends NameEntity {
    private static final long serialVersionUID = -370316274269471216L;

    public Client(){
        super();
    }

    public Client(String name){
        super();
        this.setName(name);
        this.setPinYin(PinyinUtil.getPingYin(name));
        this.setPinYinHead(PinyinUtil.getPinYinHeadChar(name));
    }

    /**
     * 客户简称
     */
    private String subName;
    /**
     * 发票抬头
     */
    private String invoiceTitle;
    /**
     * 客户性质
     */
    private Dict type;
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
     * 备注
     */
    private String remark;

    /**
     * 客户情况说明
     */
    private String situtation;


    /**
     * 联系人
     */
    private Set<Linkman> linkman;
    /**
     * 图片Ids（临时域）
     */
    private String clientPhotoIds;

    @ExcelField(title="客户性质", align=2, sort=20,value ="type.name")
    @ManyToOne(fetch = FetchType.LAZY)
    public Dict getType() {
        return type;
    }
    public void setType(Dict type) {
        this.type = type;
    }
    @ExcelField(title="省(直辖市)", align=2, sort=40,value ="province")
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    @ExcelField(title="市（区）", align=2, sort=50,value ="city")
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @ExcelField(title="县", align=2, sort=60,value ="county")
    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }
    @ExcelField(title="备注", align=2, sort=70,value ="remark")
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    @OrderBy("id asc")
    public Set<Linkman> getLinkman() {
        return linkman;
    }
    public void setLinkman(Set<Linkman> linkman) {
        this.linkman = linkman;
    }
    @ExcelField(title="发票抬头", align=2, sort=30,value ="invoiceTitle")
    public String getInvoiceTitle() {
        return invoiceTitle;
    }
    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    @Transient
    public String getClientPhotoIds() {
        return clientPhotoIds;
    }
    public void setClientPhotoIds(String clientPhotoIds) {
        this.clientPhotoIds = clientPhotoIds;
    }

    @ExcelField(title="简称", align=2, sort=10,value ="subName")
    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
    @ExcelField(title="情况说明", align=2, sort=80,value ="situtation")
    public String getSitutation() {
        return situtation;
    }

    public void setSitutation(String situtation) {
        this.situtation = situtation;
    }
}


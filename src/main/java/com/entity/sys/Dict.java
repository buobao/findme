package com.entity.sys;

import com.bean.BaseEnum;
import com.bean.DictBean;
import com.entity.sys.base.BaseEntity;

import javax.persistence.*;

/**
 * Created by dqf on 2015/8/17.
 */
@Entity
@Table(name="ss_dict")
public class Dict extends BaseEntity {
    private static final long serialVersionUID = -2755329278196422648L;

    public Dict(){
        super();
    }

    public Dict(DictBean.DictEnum dictEnum, String name, String value, int sortNo, int ifKey, String remark){
        super();
        this.dictKey = dictEnum;
        this.setName(name);
        this.setValue(value);
        this.setSortNo(sortNo);
        this.setIfKey(ifKey);
        this.setRemark(remark);
    }

    public Dict(DictBean.DictEnum dictEnum, String name, String value, int sortNo, int ifKey, String remark, String companyId){
        super();
        this.setCompanyId(companyId);

        this.dictKey = dictEnum;
        this.setName(name);
        this.setValue(value);
        this.setSortNo(sortNo);
        this.setIfKey(ifKey);
        this.setRemark(remark);
    }


    private DictBean.DictEnum dictKey;
    protected String no;
    /**
     * 排序号，如果是系统定制用3位数 100-200-300-400 自定义采用101 102 103 104 201 202 203 204
     *
     * */
    protected int sortNo;
    /**
     * 规则定制，value=值用户规则计算，比如成交生成成交记录等
     * */
    protected String value;
    /**
     * 是否关键字典（不允许禁用）1/0
     */
    protected int ifKey;
    /**
     * 不可选 1/0 默认0可选(必须ifKey=1)
     */
    protected int forceSelect;
    /**
     * 备注说明
     */
    protected String remark;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public DictBean.DictEnum getDictKey() {
        return dictKey;
    }
    public void setDictKey(DictBean.DictEnum dictKey) {
        this.dictKey = dictKey;
    }

    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public int getSortNo() {
        return sortNo;
    }
    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public int getIfKey() {
        return ifKey;
    }
    public void setIfKey(int ifKey) {
        this.ifKey = ifKey;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public int getForceSelect() {
        return forceSelect;
    }

    public void setForceSelect(int forceSelect) {
        this.forceSelect = forceSelect;
    }
    /**
     * 虚拟字段，用于映射自定义的setting
     */
    protected String sid;
    protected String sname;
    protected BaseEnum.StateEnum sstate;
    protected String scompanyId;

    @Transient
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
    @Transient
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
    @Transient
    public BaseEnum.StateEnum getSstate() {
        return sstate;
    }

    public void setSstate(BaseEnum.StateEnum sstate) {
        this.sstate = sstate;
    }
    @Transient
    public String getScompanyId() {
        return scompanyId;
    }

    public void setScompanyId(String scompanyId) {
        this.scompanyId = scompanyId;
    }
}

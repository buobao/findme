package com.entity.base;

import com.bean.BaseEnum;
import com.entity.sys.Company;
import com.entity.sys.Users;

import javax.persistence.*;

/**
 * Created by dqf on 2015/8/17.
 */
@MappedSuperclass
public class BaseEntity extends Entity {
    private static final long serialVersionUID = 5929110077551124922L;

    /**
     * 创建人
     */
    protected Users creater;
    /**
     * 所属公司
     */
    private Company company;

    protected BaseEnum.StateEnum state;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public BaseEnum.StateEnum getState() {
        return state;
    }
    public void setState(BaseEnum.StateEnum state) {
        this.state = state;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    public Users getCreater() {
        return creater;
    }
    public void setCreater(Users creater) {
        this.creater = creater;
    }
}

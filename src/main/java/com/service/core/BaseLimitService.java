package com.service.core;

import com.bean.Pager;
import com.entity.base.BaseEntity;
import com.entity.sys.Company;
import com.entity.sys.Users;
import com.service.sys.BaseEntityService;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by dqf on 2015/8/19.
 */
public interface BaseLimitService<T extends BaseEntity, PK extends Serializable> extends BaseEntityService<T, PK> {
    /**
     * 根据权限查找
     * @param pager
     * @return
     */
    public Pager findByPagerAndLimit(Pager pager, Users users, Company company, Map<String, Object> rmap);
}

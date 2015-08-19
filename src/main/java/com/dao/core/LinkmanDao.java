package com.dao.core;

import com.bean.Pager;
import com.dao.sys.BaseEntityDao;
import com.entity.core.Client;
import com.entity.core.Linkman;

/**
 * Created by amin on 2015/2/27.
 */
public interface LinkmanDao extends BaseEntityDao<Linkman,String> {
    public Pager findByPagerAndClient(Pager var1, Client client);
}

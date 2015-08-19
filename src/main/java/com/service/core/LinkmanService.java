package com.service.core;

import com.bean.Pager;
import com.entity.core.Client;
import com.entity.core.Linkman;
import com.service.sys.BaseEntityService;

import java.util.Map;

/**
 * Created by dqf on 2015/8/19.
 */
public interface LinkmanService extends BaseEntityService<Linkman,String> {
    public Pager findByPagerAndClient(Pager pager, Client client);

    /**
     * app使用
     * 转化为列表Map集合
     * @param linkman
     * @return
     */
    public Map<String, Object> getListItemMap(Linkman linkman);

    /**
     * app使用
     * 转化为明细Map集合
     * @param linkman
     * @return
     */
    public Map<String, Object> getDetailMap(Linkman linkman);
}
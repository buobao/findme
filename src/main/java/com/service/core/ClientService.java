package com.service.core;

import com.entity.core.Client;
import com.service.sys.BaseEntityService;

import java.util.Map;

/**
 * Created by dqf on 2015/8/19.
 */
public interface ClientService extends BaseEntityService<Client,String> {

    /**
     * app使用
     * 转化为列表Map集合
     * @param client
     * @return
     */
    public Map<String, Object> getListItemMap(Client client);

    /**
     * app使用
     * 转化为明细Map集合
     * @param client
     * @return
     */
    public Map<String, Object> getDetailMap(Client client);
}

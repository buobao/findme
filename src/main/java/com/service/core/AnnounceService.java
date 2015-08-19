package com.service.core;

import com.entity.core.Announce;
import com.service.sys.BaseEntityService;

import java.util.Map;

/**
 * Created by dqf on 2015/8/19.
 */
public interface AnnounceService extends BaseEntityService<Announce,String> {

    /**
     * app使用
     * 转化为列表Map集合
     * @param announce
     * @return
     */
    public Map<String, Object> getListItemMap(Announce announce);

    /**
     * app使用
     * 转化为明细Map集合
     * @param announce
     * @return
     */
    public Map<String, Object> getDetailMap(Announce announce);
}

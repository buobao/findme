package com.service.core;

import com.entity.core.Knowledge;
import com.service.sys.BaseEntityService;

import java.util.Map;

/**
 * Created by dqf on 2015/8/19.
 */
public interface KnowledgeService extends BaseEntityService<Knowledge, String> {

    /**
     * app使用
     * 转化为列表Map集合
     * @param knowledge
     * @return
     */
    public Map<String, Object> getListItemMap(Knowledge knowledge);

    /**
     * app使用
     * 转化为明细Map集合
     * @param knowledge
     * @return
     */
    public Map<String, Object> getDetailMap(Knowledge knowledge);
}

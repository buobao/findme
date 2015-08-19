package com.service.impl.core;

import com.dao.core.KnowledgeDao;
import com.dao.sys.BaseEntityDao;
import com.entity.core.Knowledge;
import com.service.core.KnowledgeService;
import com.service.impl.sys.BaseEntityServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dqf on 2015/3/9.
 */
@Service
public class KnowledgeServiceImpl  extends BaseEntityServiceImpl<Knowledge,String> implements KnowledgeService {
    @Resource
    KnowledgeDao knowledgeDao;

    @Override
    public BaseEntityDao<Knowledge, String> getBaseEntityDao() {
        return knowledgeDao;
    }

    @Override
    public Map<String, Object> getListItemMap(Knowledge knowledge) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", StringUtils.isNotEmpty(knowledge.getId())?knowledge.getId():"");
        map.put("name", StringUtils.isNotEmpty(knowledge.getName())?knowledge.getName():"");
        map.put("superType", StringUtils.isNotEmpty(knowledge.getSuperType().getName())?knowledge.getSuperType().getName():"");
        map.put("subType", StringUtils.isNotEmpty(knowledge.getSubType().getName())?knowledge.getSubType().getName():"");

        return map;
    }

    @Override
    public Map<String, Object> getDetailMap(Knowledge knowledge) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", StringUtils.isNotEmpty(knowledge.getId())?knowledge.getId():"");
        map.put("name", StringUtils.isNotEmpty(knowledge.getName())?knowledge.getName():"");
        map.put("superType", StringUtils.isNotEmpty(knowledge.getSuperType().getName())?knowledge.getSuperType().getName():"");
        map.put("subType", StringUtils.isNotEmpty(knowledge.getSubType().getName())?knowledge.getSubType().getName():"");

        if(StringUtils.isNotEmpty(knowledge.getContextHtml())){
            map.put("contextHtml","y");
        }else{
            map.put("contextHtml","");
        }

        if(StringUtils.isNotEmpty(knowledge.getKnowledgeId())){
            map.put("knowledgeId","y");
        }else{
            map.put("knowledgeId","");
        }

        return map;
    }
}

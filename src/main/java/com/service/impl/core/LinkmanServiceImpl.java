package com.service.impl.core;

import com.bean.Pager;
import com.dao.core.LinkmanDao;
import com.dao.sys.BaseEntityDao;
import com.entity.core.Client;
import com.entity.core.Linkman;
import com.service.core.LinkmanService;
import com.service.impl.sys.BaseEntityServiceImpl;
import com.utils.DataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amin on 2015/2/27.
 */
@Service
public class LinkmanServiceImpl extends BaseEntityServiceImpl<Linkman,String> implements LinkmanService {
    @Resource
    LinkmanDao linkmanDao;

    @Override
    public BaseEntityDao<Linkman, String> getBaseEntityDao() {
        return linkmanDao;
    }

    @Override
    public Pager findByPagerAndClient(Pager pager, Client client) {
        return linkmanDao.findByPagerAndClient(pager, client);
    }

    @Override
    public Map<String, Object> getListItemMap(Linkman linkman) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("id", StringUtils.isNotEmpty(linkman.getId())?linkman.getId():"");
        map.put("name", StringUtils.isNotEmpty(linkman.getName())?linkman.getName():"");
        map.put("mobile", StringUtils.isNotEmpty(linkman.getMobile())?linkman.getMobile():"");

        return map;
    }

    @Override
    public Map<String, Object> getDetailMap(Linkman linkman) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("id", StringUtils.isNotEmpty(linkman.getId())?linkman.getId():"");
        map.put("name", StringUtils.isNotEmpty(linkman.getName())?linkman.getName():"");
        if(linkman.getClient()!=null){
            map.put("clientId", linkman.getClient().getId());
            map.put("clientName", linkman.getClient().getName());
        }

        map.put("subName", StringUtils.isNotEmpty(linkman.getSubName())?linkman.getSubName():"");
        map.put("duty", StringUtils.isNotEmpty(linkman.getDuty())?linkman.getDuty():"");
        map.put("mobile", StringUtils.isNotEmpty(linkman.getMobile())?linkman.getMobile():"");
        map.put("mobile_back", StringUtils.isNotEmpty(linkman.getMobile_back())?linkman.getMobile_back():"");
        map.put("tel", StringUtils.isNotEmpty(linkman.getTel())?linkman.getTel():"");
        map.put("email", StringUtils.isNotEmpty(linkman.getEmail())?linkman.getEmail():"");
        map.put("preferences", StringUtils.isNotEmpty(linkman.getPreferences())?linkman.getPreferences():"");
        map.put("remark", StringUtils.isNotEmpty(linkman.getRemark())?linkman.getRemark():"");

        if(linkman.getBirthday()!=null){
            String tmp = DataUtil.DateToString(linkman.getBirthday(), "yyyy-MM-dd");
            map.put("birthday", tmp);
        }

        return map;
    }
}

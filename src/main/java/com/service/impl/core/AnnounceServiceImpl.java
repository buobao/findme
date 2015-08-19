package com.service.impl.core;

import com.dao.core.AnnounceDao;
import com.dao.sys.BaseEntityDao;
import com.entity.core.Announce;
import com.entity.sys.Users;
import com.service.core.AnnounceService;
import com.service.impl.sys.BaseEntityServiceImpl;
import com.utils.DataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by amin on 2015/2/27.
 */
@Service
public class AnnounceServiceImpl extends BaseEntityServiceImpl<Announce,String> implements AnnounceService {
    @Resource
    AnnounceDao announceDao;

    @Override
    public BaseEntityDao<Announce, String> getBaseEntityDao() {
        return announceDao;
    }

    @Override
    public Map<String, Object> getListItemMap(Announce announce) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", StringUtils.isNotEmpty(announce.getId())?announce.getId():"");
        map.put("name", StringUtils.isNotEmpty(announce.getName())?announce.getName():"");
        map.put("content", StringUtils.isNotEmpty(announce.getContent())?announce.getContent():"");

        String pdat = DataUtil.DateToString(announce.getPublishTime(), "yyyy-MM-dd");
        map.put("publishTime", StringUtils.isNotEmpty(pdat) ?pdat:"");

        return map;
    }

    @Override
    public Map<String, Object> getDetailMap(Announce announce) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", StringUtils.isNotEmpty(announce.getId())?announce.getId():"");
        map.put("name", StringUtils.isNotEmpty(announce.getName())?announce.getName():"");
        map.put("content", StringUtils.isNotEmpty(announce.getContent())?announce.getContent():"");

        String pdat = DataUtil.DateToString(announce.getPublishTime(), "yyyy-MM-dd");
        map.put("publishTime", StringUtils.isNotEmpty(pdat)?pdat:"");

        int type = announce.getCallType();
        String usersSet = "";
        if(type==1){
            Set<Users> users = announce.getUsersSet();
            if(users.size()>0){
                for(Users u:users){
                    usersSet += u.getName() + " ";
                }
            }
        }else{
            usersSet = "全体成员";
        }
        map.put("usersSet", usersSet);
        return map;
    }
}

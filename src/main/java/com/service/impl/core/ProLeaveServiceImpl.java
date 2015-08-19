package com.service.impl.core;

import com.dao.core.ProLeaveDao;
import com.dao.sys.BaseEntityDao;
import com.entity.core.ProLeave;
import com.service.core.ProLeaveService;
import com.utils.DataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XuHeng on 2015/4/17.
 */
@Service
public class ProLeaveServiceImpl extends BaseLimitServiceImpl<ProLeave,String> implements ProLeaveService {
    @Resource
    ProLeaveDao proLeaveDao;

    @Override
    public BaseEntityDao<ProLeave, String> getBaseEntityDao() {
        return proLeaveDao;
    }

    /**
     * app用
     * @param proLeave
     * @return
     */
    @Override
    public Map<String, Object> getDetailMap(ProLeave proLeave) {
        String tmp;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", StringUtils.isNotEmpty(proLeave.getId())?proLeave.getId():"");

        if (proLeave.getProInfo() != null) {
            map.put("proInfoName", proLeave.getProInfo().getName());
            map.put("proInfoId", proLeave.getProInfo().getId());
        }else{
            map.put("proInfoName", "");
            map.put("proInfoId", "");
        }

//        map.put("proInfoName", StringUtils.isNotEmpty(proLeave.getName())?proLeave.getName():"");
        map.put("reason", StringUtils.isNotEmpty(proLeave.getReason())?proLeave.getReason():"");

        if (proLeave.getLeaveDate() != null) {
            tmp = DataUtil.DateToString(proLeave.getLeaveDate(), "yyyy-MM-dd");
            map.put("leaveDate", tmp);
        }else{
            map.put("leaveDate", "");
        }

        if(proLeave.getCreater()!=null){
            map.put("createrName", proLeave.getCreater().getName());
        }else{
            map.put("createrName", "");
        }

        if (proLeave.getCreateDate() != null) {
            tmp = DataUtil.DateToString(proLeave.getCreateDate(), "yyyy-MM-dd HH:mm");
            map.put("createDate", tmp);
        }else{
            map.put("createDate", "");
        }
        return map;
    }
}

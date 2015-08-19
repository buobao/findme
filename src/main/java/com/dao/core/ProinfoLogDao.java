package com.dao.core;

import com.dao.sys.BaseEntityDao;
import com.entity.core.ProInfo;
import com.entity.core.ProInfoLog;

import java.util.List;

/**
 * Created by amin on 2015/4/20.
 */
public interface ProinfoLogDao extends BaseEntityDao<ProInfoLog,String> {
    public List<ProInfoLog> getByProInfo(ProInfo proInfo);
}

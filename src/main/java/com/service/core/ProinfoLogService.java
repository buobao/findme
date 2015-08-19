package com.service.core;

import com.entity.core.ProInfo;
import com.entity.core.ProInfoLog;
import com.entity.sys.Users;
import com.service.sys.BaseEntityService;

import java.util.List;
import java.util.Set;

/**
 * Created by dqf on 2015/8/19.
 */
public interface ProinfoLogService extends BaseEntityService<ProInfoLog,String> {

    /**
     * 项目总监或成员变更时，记录log
     */
    public void updateProLog(ProInfo proInfo,Users users,Set<Users> group);

    /**
     * 根据项目得到项目log
     * @param proInfo
     * @return
     */
    public List<ProInfoLog> getByProInfo(ProInfo proInfo);

    /**
     * 项目组成员变更
     * @param proInfo
     * @param proInfoLog
     */
    public void updateProInfoLog(ProInfo proInfo,ProInfoLog proInfoLog);
}

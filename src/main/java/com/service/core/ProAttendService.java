package com.service.core;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.entity.core.ProAttend;
import com.entity.core.ProInfo;
import com.service.sys.BaseEntityService;

import java.util.List;

/**
 * Created by dqf on 2015/8/19.
 */
public interface ProAttendService extends BaseEntityService<ProAttend,String> {

    /**
     * 根据项目查找该项目所有的考勤设置
     * @param pager
     * @param proInfo
     * @param states
     * @return
     */
    public Pager findByPagerAndProInfo(Pager pager, ProInfo proInfo, BaseEnum.StateEnum[] states);

    /**
     * 根据项目信息获得考勤设置
     * @param proInfo
     * @param states
     * @return
     */
    public List<ProAttend> getListByProInfo(ProInfo proInfo, BaseEnum.StateEnum[] states);
}

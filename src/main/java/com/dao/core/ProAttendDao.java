package com.dao.core;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.dao.sys.BaseEntityDao;
import com.entity.core.ProAttend;
import com.entity.core.ProInfo;

import java.util.List;

/**
 * Created by amin on 2015/2/27.
 */
public interface ProAttendDao extends BaseEntityDao<ProAttend,String> {
    /**
     * 根据项目查找该项目所有的考勤设置
     * @param pager
     * @param proInfo
     * @param states
     * @return
     */
    public Pager findByPagerAndProInfo(Pager pager, ProInfo proInfo, BaseEnum.StateEnum[] states);


    public List<ProAttend> getListByProInfo(ProInfo proInfo, BaseEnum.StateEnum[] states);
}

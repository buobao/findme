package com.dao.core;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.dao.sys.BaseEntityDao;
import com.entity.core.ProBack;
import com.entity.core.ProInfo;

/**
 * Created by amin on 2015/2/27.
 */
public interface ProBackDao extends BaseEntityDao<ProBack,String> {
    /**
     * 根据项目查找该项目所有的考勤设置
     * @param pager
     * @param proInfo
     * @param states
     * @return
     */
    public Pager findByPagerAndProInfo(Pager pager, ProInfo proInfo, BaseEnum.StateEnum[] states);
}

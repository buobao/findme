package com.service.core;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.entity.core.ProBack;
import com.entity.core.ProInfo;
import com.entity.sys.Locations;

import java.util.Map;

/**
 * Created by dqf on 2015/8/19.
 */
public interface ProBackService extends BaseLimitService<ProBack,String> {
    /**
     * app使用
     * 转化为列表Map集合
     * @param proBack
     * @return
     */
    public Map<String, Object> getListItemMap(ProBack proBack);

    /**
     * app使用
     * 转化为明细Map集合
     * @param proBack
     * @return
     */
    public Map<String, Object> getDetailMap(ProBack proBack);
    /**
     * 根据项目查找该项目所有的反馈设置
     * @param pager
     * @param proInfo
     * @param states
     * @return
     */
    public Pager findByPagerAndProInfo(Pager pager, ProInfo proInfo, BaseEnum.StateEnum[] states);

    /**
     * 更新和保存的是加入履历
     * @return
     */
    public ProBack updateAndEnable(ProBack proBack);
    /**
     * 更新和保存的是加入履历
     * @return
     */
    public ProBack updateAndResume(ProBack proBack, ProBack preBack,Locations locations);
}


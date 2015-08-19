package com.service.core;

import com.entity.core.ProLeave;

import java.util.Map;

/**
 * Created by dqf on 2015/8/19.
 */
public interface ProLeaveService extends BaseLimitService<ProLeave,String> {
    /**
     * app使用
     * 转化为明细Map集合
     * @param proLeave
     * @return
     */
    public Map<String, Object> getDetailMap(ProLeave proLeave);
}


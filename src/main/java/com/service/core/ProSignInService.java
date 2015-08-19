package com.service.core;

import com.entity.core.ProSignIn;
import com.entity.sys.Locations;

import java.util.Map;

/**
 * Created by dqf on 2015/8/19.
 */
public interface ProSignInService extends BaseLimitService<ProSignIn,String> {
    /**
     * app使用
     * 转化为明细Map集合
     * @param proSignIn
     * @return
     */
    public Map<String, Object> getDetailMap(ProSignIn proSignIn);
    /**
     * 保存
     * @param signin
     * @param locations
     * @return
     */
    public void saveEntity(ProSignIn signin, Locations locations);
}

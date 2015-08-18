package com.service.sys;

import com.entity.sys.Locations;

/**
 * Created by dqf on 2015/8/18.
 */
public interface LocationsService extends BaseEntityService<Locations, String> {

    public void clearHistoryLocations();

    public Locations getLastLocation(String openId);

    /**
     * 获取当前坐标的位置信息
     * */
    public String getAddress(Locations locations);

}
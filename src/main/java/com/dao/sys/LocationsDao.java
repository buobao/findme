package com.dao.sys;

import com.entity.sys.Locations;

/**
 * Created by dqf on 2015/8/18.
 */
public interface LocationsDao extends BaseEntityDao<Locations, String> {

    public Locations getLastLocation(String openId);
}

package com.dao.sys;

import com.dao.base.BaseDao;
import com.entity.sys.LocationEntity;

/**
 * Created by dqf on 2015/8/18.
 */
public interface LocationEntityDao extends BaseDao<LocationEntity, String> {

    public LocationEntity getLocationByTargetId(String targetId);
}

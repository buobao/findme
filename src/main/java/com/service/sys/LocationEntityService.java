package com.service.sys;

import com.entity.sys.LocationEntity;
import com.service.base.BaseService;

/**
 * Created by dqf on 2015/8/18.
 */
public interface LocationEntityService extends BaseService<LocationEntity, String> {

    public LocationEntity getLocationByTargetId(String targetId);
}

package com.service.impl.sys;

import com.dao.base.BaseDao;
import com.dao.sys.LocationEntityDao;
import com.entity.sys.LocationEntity;
import com.service.impl.base.BaseServiceImpl;
import com.service.sys.LocationEntityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Service实现类 - 位置信息
 * ============================================================================
 * 版权所有 2013 qshihua。
 * ----------------------------------------------------------------------------
 * 
 * @author qshihua
 * 
 * @version 0.1 2013-05-06
 */

@Service
public class LocationEntityServiceImpl extends BaseServiceImpl<LocationEntity, String> implements LocationEntityService {

	@Resource
	private LocationEntityDao locationEntityDao;

    @Override
    public BaseDao<LocationEntity, String> getBaseDao() {
        return locationEntityDao;
    }

    public LocationEntity getLocationByTargetId(String targetId){
        return locationEntityDao.getLocationByTargetId(targetId);
    }


}
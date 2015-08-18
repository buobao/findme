package com.dao.impl.sys;

import com.dao.impl.base.BaseDaoImpl;
import com.dao.sys.LocationEntityDao;
import com.entity.sys.LocationEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by dqf on 2015/8/18.
 */
@Repository
public class LocationEntityDaoImpl extends BaseDaoImpl<LocationEntity, String> implements LocationEntityDao {
    @Override
    public LocationEntity getLocationByTargetId(String targetId) {
        String hql = "from LocationEntity where lower(targetId) = lower(:targetId) ";
        return (LocationEntity) getSession().createQuery(hql).setParameter("targetId",targetId).uniqueResult();
    }
}

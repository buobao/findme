package com.dao.impl.sys;

import com.dao.sys.LocationsDao;
import com.entity.sys.Locations;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * Created by dqf on 2015/8/18.
 */
@Repository
public class LocationsDaoImpl extends BaseEntityDaoImpl<Locations, String> implements LocationsDao {
    @Override
    public Locations getLastLocation(String openId) {
        if (StringUtils.isEmpty(openId) == true){
            return null;
        }
        String hql = "from Locations locations where locations.openId = :openId order by createDate desc";
        Locations locations = (Locations)getSession().createQuery(hql).setParameter("openId",openId).setMaxResults(1).uniqueResult();

        return locations;
    }
}

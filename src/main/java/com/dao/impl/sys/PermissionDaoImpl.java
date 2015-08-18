package com.dao.impl.sys;

import com.dao.sys.PermissionDao;
import com.entity.sys.Permission;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
@Repository
public class PermissionDaoImpl extends BaseEntityDaoImpl<Permission, String> implements PermissionDao {
    @Override
    public Permission getByKey(String businessKey) {
        return null;
    }

    @Override
    public List<Permission> findAllByType(String type) {
        return null;
    }

    @Override
    public List<Permission> getAllByUser(String userId) {
        return null;
    }

    @Override
    public void updateState(String key, String keyId) {
        Assert.notNull(key, "key is required");
        Assert.notNull(keyId,"keyId is required");
        String sql="update tr_"+key+" SET state=:s where id=:d";
        getSession().createSQLQuery(sql).setParameter("s","Delete").setParameter("d",keyId).executeUpdate();
    }
}

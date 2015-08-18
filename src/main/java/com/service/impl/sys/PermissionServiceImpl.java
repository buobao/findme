package com.service.impl.sys;

import com.dao.sys.BaseEntityDao;
import com.dao.sys.PermissionDao;
import com.entity.sys.Permission;
import com.service.sys.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Service实现类 - 
 * ============================================================================
 * 版权所有 2013
 * ----------------------------------------------------------------------------
 * 
 * @author 
 * 
 * @version 0.1 2011-6-13
 */

@Service
public class PermissionServiceImpl extends BaseEntityServiceImpl<Permission, String> implements PermissionService {
    @Resource
    PermissionDao permissionDao;

    @Override
    public BaseEntityDao<Permission, String> getBaseEntityDao() {
        return permissionDao;
    }

    @Override
    public Permission getByKey(String businessKey) {
        return permissionDao.getByKey(businessKey);
    }

    @Override
    public List<Permission> findAllByType(String type) {
        return permissionDao.findAllByType(type);
    }

    @Override
    public List<Permission> getAllByUser(String userId) {
        return permissionDao.getAllByUser(userId);
    }

    @Override
    public void updateState(String key, String keyId) {
        permissionDao.updateState(key,keyId);
    }
}
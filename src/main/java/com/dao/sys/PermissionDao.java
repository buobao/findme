package com.dao.sys;

import com.entity.sys.Permission;

import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
public interface PermissionDao extends BaseEntityDao<Permission, String> {
    /**
     * 根据key来查找Permission
     * @param businessKey
     * @return
     */
    public Permission getByKey(String businessKey);

    /**
     * 根据资源类型查找
     * @param type
     * @return
     */
    public List<Permission> findAllByType(String type);

    /**
     * 根据Id来获取到当前人访问的资源
     * @param userId
     * @return
     */
    public List<Permission> getAllByUser(String userId);

    /**
     * 更新实体的文档状态
     */
    public void updateState(String key, String keyId);
}

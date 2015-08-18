package com.service.sys;

import com.entity.sys.Role;
import com.entity.sys.Users;

import java.util.Set;

/**
 * Created by dqf on 2015/8/18.
 */
public interface RoleService extends BaseEntityService<Role, String> {
    /**
     * 根据users别名查找role的别名
     * @param users
     * @return
     */
    public Set<String> findPnameByUser(Users users);
}

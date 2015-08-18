package com.dao.sys;

import com.entity.sys.Role;
import com.entity.sys.Users;

import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
public interface RoleDao extends BaseEntityDao<Role, String> {

    /**
     * 根据用户查找角色
     * @param users
     * @return
     */
    public List<String> findPnameByUser(Users users);
}

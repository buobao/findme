package com.dao.impl.sys;

import com.dao.sys.RoleDao;
import com.entity.sys.Role;
import com.entity.sys.Users;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
@Repository
public class RoleDaoImpl extends BaseEntityDaoImpl<Role, String> implements RoleDao {


    @Override
    public List<String> findPnameByUser(Users users) {
        String sql = "SELECT ru.roleSet_id FROM sys_role_sys_users AS ru WHERE ru.usersSet_id=:u";
        List<String> roleList = getSession().createSQLQuery(sql).setParameter("u", users.getId()).list();
        if(roleList == null){
            return Lists.newArrayList();
        }
        return roleList;
    }
}

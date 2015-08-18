package com.dao.impl.sys;

import com.bean.BaseEnum;
import com.bean.EnumManage;
import com.dao.sys.UsersDao;
import com.entity.sys.Admin;
import com.entity.sys.Department;
import com.entity.sys.Users;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
@Repository
public class UsersDaoImpl extends BaseEntityDaoImpl<Users, String> implements UsersDao {

    @Override
    public Users getUsersByMobile(String mobile) {
        String hql = "from " + Users.class.getName() + " model where model.mobile = ? and model.state = ?";
        List<Users> usersList = getSession().createQuery(hql).setParameter(0, mobile).setParameter(1, BaseEnum.StateEnum.Enable).list();
        return usersList.size()>0 ? usersList.get(0) : null;
    }

    @Override
    public Users getUsersByMobileDel(String mobile) {
        String hql = "from " + Users.class.getName() + " model where model.mobile = ? and model.state = ?";
        List<Users> usersList = getSession().createQuery(hql).setParameter(0, mobile).setParameter(1, BaseEnum.StateEnum.Delete).list();
        return usersList.size()>0 ? usersList.get(0) : null;
    }

    @Override
    public Users getUsersByEmail(String email) {
        String hql = "from " + Users.class.getName() + " model where model.email = :email and model.state = :state ";
        List<Users> usersList = getSession().createQuery(hql).setParameter("email", email).setParameter("state", BaseEnum.StateEnum.Enable).list();
        return usersList.size()>0 ? usersList.get(0) : null;
    }



    @Override
    public List<Department> getUsersPrincipal(Users users) {
        Assert.notNull(users, "users is required");
        String hql = "from Department where id in ( select distinct(d.id) FROM Department d where d.state = :state and ( d.id in (select duty.department.id from Duty duty where duty.state = :state and duty.users.id = :usersId and duty.dutyState = :dutyState) or d.parent.id in (select duty.department.id  from Duty duty where duty.state = :state and duty.users.id = :usersId and duty.dutyState = :dutyState )))";
        return getSession().createQuery(hql).setParameter("state", BaseEnum.StateEnum.Enable).setParameter("usersId", users.getId()).setParameter("dutyState", EnumManage.DutyState.Principal).list();
    }

    @Override
    public Users getByAdmin(Admin admin) {
        String hql = "from " + Users.class.getName() + " model where model.adminId = ? and model.state = ?";
        List<Users> usersList = getSession().createQuery(hql).setParameter(0, admin.getId()).setParameter(1, BaseEnum.StateEnum.Enable).list();
        return usersList.size()>0 ? usersList.get(0) : null;
    }




}

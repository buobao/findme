package com.dao.impl.sys;

import com.bean.BaseEnum;
import com.dao.sys.PowerDao;
import com.entity.sys.Department;
import com.entity.sys.Post;
import com.entity.sys.Power;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
@Repository
public class PowerDaoImpl extends BaseEntityDaoImpl<Power, String> implements PowerDao {
    @Override
    public List<Power> getByDepartId(String departId) {
        String hql="from Power as p where p.department.id=? and p.state=?";
        return (List<Power>)getSession().createQuery(hql).setParameter(0,departId).setParameter(1, BaseEnum.StateEnum.Enable).list();
    }

    @Override
    public Power getPowerByDepartAndPost(Department department, Post post) {
        String hql="from Power as p where p.department.id=? and p.post.id=? and p.state=?";
        return (Power)getSession().createQuery(hql).setParameter(0,department.getId()).setParameter(1,post.getId()).setParameter(2, BaseEnum.StateEnum.Enable).list().get(0);
    }
}

package com.dao.impl.sys;

import com.bean.BaseEnum;
import com.dao.sys.DepartmentDao;
import com.entity.sys.Department;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
@Repository
public class DepartmentDaoImpl extends BaseEntityDaoImpl<Department, String> implements DepartmentDao {
    @Override
    public List<Department> getDepartments(String name) {
        Assert.notNull(name,"name is required");
        String hql = " from Department as d where d.name=:n and d.state=:s";
        return getSession().createQuery(hql).setParameter("n",name).setParameter("s", BaseEnum.StateEnum.Enable).list();
    }
}





































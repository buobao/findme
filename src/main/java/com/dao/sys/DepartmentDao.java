package com.dao.sys;

import com.entity.sys.Department;

import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
public interface DepartmentDao extends BaseEntityDao<Department, String> {

    public List<Department> getDepartments(String name);

}

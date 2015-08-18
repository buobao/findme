package com.service.sys;

import com.entity.sys.Department;

import java.util.List;
import java.util.Set;

/**
 * Created by dqf on 2015/8/18.
 */
public interface DepartmentService extends BaseEntityService<Department, String> {

    /**
     * 根据名称查找
     *
     */
    public List<Department> getDepartments(String name);

    public Set<Department> getChildDepart(Department department,Set<Department> set);
}

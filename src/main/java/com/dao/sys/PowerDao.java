package com.dao.sys;

import com.entity.sys.Department;
import com.entity.sys.Post;
import com.entity.sys.Power;

import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
public interface PowerDao extends BaseEntityDao<Power, String> {
    /**
     * 根据departId查找power
     * @param departId
     * @return
     */
    public List<Power> getByDepartId(String departId);

    /**
     * 根据department和post查找power
     * @param department
     * @param post
     * @return
     */
    public Power getPowerByDepartAndPost(Department department, Post post);

}

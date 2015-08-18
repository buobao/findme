package com.service.sys;

import com.entity.sys.Department;
import com.entity.sys.Duty;
import com.entity.sys.Post;
import com.entity.sys.Power;

import java.util.List;
import java.util.Set;

/**
 * Created by dqf on 2015/8/18.
 */
public interface PowerService extends BaseEntityService<Power, String> {
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

    /**
     * 获取当前职责的上一级职权的职责
     * @param duty
     * @return
     */
    public Set<Duty> findParentByPower(Duty duty);


}

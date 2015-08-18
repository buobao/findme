package com.dao.sys;

import com.entity.sys.Post;

/**
 * Created by dqf on 2015/8/18.
 */
public interface PostDao extends BaseEntityDao<Post, String> {
    /**
     * 根据岗位名称查找岗位
     * @param name -- 岗位名称
     * @return
     */
    public Post getPostByName(String name);
}

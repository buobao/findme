package com.service.sys;

import com.entity.sys.Post;

/**
 * Created by dqf on 2015/8/18.
 */
public interface PostService extends BaseEntityService<Post, String> {
    /**
     * 根据名称查找
     *
     */
    public Post getPostByName(String name);
}

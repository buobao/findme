package com.dao.impl.sys;

import com.bean.BaseEnum;
import com.dao.sys.PostDao;
import com.entity.sys.Post;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
@Repository
public class PostDaoImpl extends BaseEntityDaoImpl<Post, String> implements PostDao {
    @Override
    public Post getPostByName(String name) {
        Assert.notNull(name, "name is required");
        String hql = "from Post p where p.name=? and p.state = ?";
        List<Post> postList = (List<Post>) getSession().createQuery(hql).setParameter(0,name).setParameter(1, BaseEnum.StateEnum.Enable);
        if(postList.size()>0){
            return postList.get(0);
        }else {
            return null;
        }
    }
}





































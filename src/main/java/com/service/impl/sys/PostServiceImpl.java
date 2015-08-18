package com.service.impl.sys;

import com.dao.sys.BaseEntityDao;
import com.dao.sys.PostDao;
import com.entity.sys.Post;
import com.service.sys.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Service实现类 - 
 * ============================================================================
 * 版权所有 2013
 * ----------------------------------------------------------------------------
 * 
 * @author 
 * 
 * @version 0.1 2011-6-13
 */

@Service
public class PostServiceImpl extends BaseEntityServiceImpl<Post, String> implements PostService {
	
	@Resource
	PostDao postDao;


	@Override
	public BaseEntityDao<Post, String> getBaseEntityDao() {
		return postDao;
	}

	
	public Post getPostByName(String name) {
		return postDao.getPostByName(name);
	}


}
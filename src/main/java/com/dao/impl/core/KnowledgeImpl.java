package com.dao.impl.core;

import com.dao.core.KnowledgeDao;
import com.dao.impl.sys.BaseEntityDaoImpl;
import com.entity.core.Knowledge;
import org.springframework.stereotype.Repository;

/**
 * Created by dqf on 2015/3/9.
 */
@Repository
public class KnowledgeImpl  extends BaseEntityDaoImpl<Knowledge,String> implements KnowledgeDao {
}

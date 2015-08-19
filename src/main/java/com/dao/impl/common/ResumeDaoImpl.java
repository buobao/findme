package com.dao.impl.common;

import com.dao.common.ResumeDao;
import com.dao.impl.base.MongoBaseDaoImpl;
import com.entity.module.Resume;
import org.springframework.stereotype.Repository;

/**
 * Created by dqf on 2015/8/19.
 */
@Repository
public class ResumeDaoImpl extends MongoBaseDaoImpl<Resume, String> implements ResumeDao {

}
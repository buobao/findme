package com.dao.impl.core;

import com.dao.core.AnnounceDao;
import com.dao.impl.sys.BaseEntityDaoImpl;
import com.entity.core.Announce;
import org.springframework.stereotype.Repository;

/**
 * Created by amin on 2015/2/27.
 */
@Repository
public class AnnounceDaoImpl extends BaseEntityDaoImpl<Announce,String> implements AnnounceDao {
}

package com.dao.impl.core;

import com.bean.BaseEnum;
import com.dao.core.ProinfoLogDao;
import com.dao.impl.sys.BaseEntityDaoImpl;
import com.entity.core.ProInfo;
import com.entity.core.ProInfoLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by amin on 2015/4/20.
 */
@Repository
public class ProinfoLogDaoImpl extends BaseEntityDaoImpl<ProInfoLog,String> implements ProinfoLogDao {
    @Override
    public List<ProInfoLog> getByProInfo(ProInfo proInfo) {
        String hql="from ProInfoLog as p where p.proInfo.id=:d and p.state=:s order by p.createDate desc";
        return getSession().createQuery(hql).setParameter("d",proInfo.getId()).setParameter("s", BaseEnum.StateEnum.Enable).list();
    }
}

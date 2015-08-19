package com.dao.impl.core;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.dao.core.LinkmanDao;
import com.dao.impl.sys.BaseEntityDaoImpl;
import com.entity.core.Client;
import com.entity.core.Linkman;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by amin on 2015/2/27.
 */
@Repository
public class LinkmanDaoImpl extends BaseEntityDaoImpl<Linkman,String> implements LinkmanDao {
    @Override
    public Pager findByPagerAndClient(Pager pager, Client client) {
        if (pager == null) {
            pager = new Pager();
        }
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
        detachedCriteria.add(Restrictions.eq("state", BaseEnum.StateEnum.Enable));

        if (client != null) {
            detachedCriteria.createAlias("client", "client");
            detachedCriteria.add(Restrictions.or(
                    Restrictions.eq("client", client),
                    Restrictions.eq("client.id", client.getId())));
        }
        return findByPager(pager, detachedCriteria);
    }
}

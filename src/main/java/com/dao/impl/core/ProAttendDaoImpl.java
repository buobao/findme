package com.dao.impl.core;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.dao.core.ProAttendDao;
import com.dao.impl.sys.BaseEntityDaoImpl;
import com.entity.core.ProAttend;
import com.entity.core.ProInfo;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by amin on 2015/2/27.
 */
@Repository
public class ProAttendDaoImpl extends BaseEntityDaoImpl<ProAttend,String> implements ProAttendDao {
    @Override
    public List<ProAttend> getListByProInfo(ProInfo proInfo, BaseEnum.StateEnum[] states) {
        String hql = "from " + ProAttend.class.getName() + " model where model.proInfo.id = :proInfo and model.state in :state ";
        List<ProAttend> list = getSession().createQuery(hql).setParameter("proInfo",proInfo.getId()).setParameter("state", BaseEnum.StateEnum.Enable ).list();
        return list;
    }

    @Override
    public Pager findByPagerAndProInfo(Pager pager, ProInfo proInfo, BaseEnum.StateEnum[] states) {
        if (pager == null) {
            pager = new Pager();
        }

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(new ProAttend().getClass());
        if (states != null && states.length > 0) {
            Criterion[] criterions = new Criterion[states.length];
            for (int i = 0; i < states.length; i++) {
                criterions[i] = Restrictions.eq("state", states[i]);
            }
            detachedCriteria.add(Restrictions.or(criterions));
        }
        if (proInfo != null) {
            detachedCriteria.createAlias("proInfo", "proInfo");
            detachedCriteria.add(Restrictions.or(
                    Restrictions.eq("proInfo", proInfo),
                    Restrictions.eq("proInfo.id", proInfo.getId())));
        }

        return findByPager(pager, detachedCriteria);
    }

}

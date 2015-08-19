package com.dao.impl.core;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.dao.core.ProBackDao;
import com.dao.impl.sys.BaseEntityDaoImpl;
import com.dao.sys.AdminDao;
import com.dao.sys.UsersDao;
import com.entity.core.ProBack;
import com.entity.core.ProInfo;
import com.entity.sys.Admin;
import com.entity.sys.Company;
import com.entity.sys.Users;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by amin on 2015/2/27.
 */
@Repository
public class ProBackDaoImpl extends BaseEntityDaoImpl<ProBack,String> implements ProBackDao {
    @Resource
    private UsersDao usersDao;
    @Resource
    private AdminDao adminDao;

    @Override
    public Pager findByPagerAndProInfo(Pager pager, ProInfo proInfo, BaseEnum.StateEnum[] states) {
        if (pager == null) {
            pager = new Pager();
        }

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(new ProBack().getClass());
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
        Subject subject= SecurityUtils.getSubject();
        String userId = (String) subject.getPrincipal();
        if(userId == null){
            return null;
        }
        Admin admin = adminDao.getByMobile(userId);
        Users users = usersDao.getByAdmin(admin);
        detachedCriteria.add(Restrictions.or(
                Restrictions.eq("creater", users),
                Restrictions.sqlRestriction("this_.id in(select id from ec_proback_notice as epn where epn.userId='" + users.getId() + "')")
        ));

        Company company = users.getCompany();
        Map<String,Object> params = Maps.newHashMap();
        params.put("company",company);
        return findByPager(pager,detachedCriteria,null,null,null,null,params);
    }
}

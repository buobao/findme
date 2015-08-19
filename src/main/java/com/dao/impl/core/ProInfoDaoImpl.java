package com.dao.impl.core;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.dao.core.ProInfoDao;
import com.dao.impl.sys.BaseEntityDaoImpl;
import com.dao.sys.AdminDao;
import com.dao.sys.UsersDao;
import com.entity.core.Client;
import com.entity.core.ProInfo;
import com.entity.sys.Admin;
import com.entity.sys.Company;
import com.entity.sys.Users;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by amin on 2015/2/27.
 */
@Repository
public class ProInfoDaoImpl extends BaseEntityDaoImpl<ProInfo,String> implements ProInfoDao {
    @Resource
    UsersDao usersDao;
    @Resource
    AdminDao adminDao;

    @Override
    public Pager findByPagerAndCompanyInBuild(Pager pager ,Map<String,Object> rmap) {
        if (pager == null) {
            pager = new Pager();
        }

        return findByPager(pager,null,null,null,null,null,rmap);
    }

    @Override
    public Pager findByPagerAndCompanyInFinish(Pager pager, Map<String,Object> rmap) {
        if (pager == null) {
            pager = new Pager();
        }
        return findByPager(pager,null,null,null,null,null,rmap);
    }

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

    @Override
    public List<String> findByDepartmentOrUser(List<String> list,Users users) {
        String sql;
        List<String> allList= Lists.newArrayList();
        Subject subject= SecurityUtils.getSubject();
        String userId = (String) subject.getPrincipal();
        if(userId == null){
            return null;
        }
        Admin admin = adminDao.getByMobile(userId);
        Users user = usersDao.getByAdmin(admin);
        if(users!=null){
            sql= "SELECT e.id from ec_proinfo as e\n" +
                    "WHERE e.id IN (SELECT u.id FROM ec_proinfo_group u WHERE u.userId " +
                    "in(SELECT duty.users_id from sys_duty duty WHERE duty.department_id=:n AND duty.users_id='"+users.getId()+"')) and e.state='Enable' OR e.chief_id='"+user.getId()+"'";

        }else {
            sql = "SELECT e.id from ec_proinfo as e\n" +
                    "WHERE e.id IN (SELECT u.id FROM ec_proinfo_group u WHERE u.userId " +
                    "in(SELECT duty.users_id from sys_duty duty WHERE duty.department_id=:n)) and e.state='Enable' OR e.chief_id='"+user.getId()+"'";
        }
        for(String s:list){
            list=getSession().createSQLQuery(sql).setParameter("n",s).list();
            allList.addAll(list);
        }
        return allList;
    }

    @Override
    public List<ProInfo> getList(BaseEnum.StateEnum[] states) {
        String hql = "from " + ProInfo.class.getName() + " model where model.state in :state ";
        List<ProInfo> list = getSession().createQuery(hql).setParameter("state", BaseEnum.StateEnum.Enable ).list();
        return list;
    }

    @Override
    public Pager findByProInfoPager(Pager pager, Set<Users> usersSet, Company company, ProInfo proinfo, Map<String,Object> params) {
        if(pager==null){
            pager=new Pager(0);
        }
        List<String> aliasList = new ArrayList<>();
        DetachedCriteria detachedCriteria= DetachedCriteria.forClass(Users.class);
        if (company != null) {
            detachedCriteria.createAlias("company", "company");
            if(!aliasList.contains("company"))
                aliasList.add("company");
            detachedCriteria.add(Restrictions.or(
                    Restrictions.eq("company", company),
                    Restrictions.eq("company.id", company.getId())));
        }
        String ids ="";
        if(usersSet!=null){
            for(Users users:usersSet){
                ids = ids + users.getId()+',';
            }
            ids = ids.substring(0,ids.length()-1);
        }
        if( proinfo != null){
            detachedCriteria.add(Restrictions.sqlRestriction("this_.id in ( select userId from ec_proinfo_group where id = '" + proinfo.getId() + "' ) "));
        }
        if (params == null) {
            params = new HashMap<String, Object>();
        }
        params.put("state", new BaseEnum.StateEnum[]{BaseEnum.StateEnum.Enable});
        return findByPager(pager,detachedCriteria,aliasList,null,null,null,params);
    }

    @Override
    public Pager findChiefByPager(Pager pager, ProInfo proinfo, Map<String, Object> params) {
        if(pager==null){
            pager=new Pager(0);
        }
        List<String> aliasList = new ArrayList<>();
        DetachedCriteria detachedCriteria= DetachedCriteria.forClass(Users.class);

        if( proinfo != null){
            detachedCriteria.add(Restrictions.sqlRestriction("this_.id in ( select chief_id from ec_proinfo where id = '" + proinfo.getId() + "' ) "));
        }
        if (params == null) {
            params = new HashMap<String, Object>();
        }
        params.put("state", new BaseEnum.StateEnum[]{BaseEnum.StateEnum.Enable});
        return findByPager(pager,detachedCriteria,aliasList,null,null,null,params);
    }

}

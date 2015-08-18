package com.dao.impl.sys;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.dao.impl.base.BaseDaoImpl;
import com.dao.sys.BaseEntityDao;
import com.entity.base.BaseEntity;
import com.entity.sys.Company;
import com.entity.sys.Users;
import com.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dqf on 2015/8/18.
 */
@Repository
public abstract class BaseEntityDaoImpl<T extends BaseEntity, PK extends Serializable> extends BaseDaoImpl<T, PK> implements BaseEntityDao<T, PK> {
    public Class<T> entityClass;

    public BaseEntityDaoImpl(){
        this.entityClass = null;
        Class<?> c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType){
            Type[] parameterizedType = ((ParameterizedType)type).getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
    }

    @Override
    public Pager findByPagerAndStates(Pager pager, BaseEnum.StateEnum[] states) {
        if (pager == null){
            pager = new Pager();
        }
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);

        if (states != null && states.length > 0){
            Criterion[] criterions = new Criterion[states.length];
            for (int i=0;i<states.length;i++){
                criterions[i] = Restrictions.eq("state",states[i]);
            }
            detachedCriteria.add(Restrictions.or(criterions));
        }

        return findByPager(pager,detachedCriteria);
    }

    @Override
    public Pager queryValidByPager(Pager pager) {
        if (pager == null){
            pager = new Pager();
        }
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
        detachedCriteria.add(Restrictions.ne("state",BaseEnum.StateEnum.Delete.value()));

        return findByPager(pager, detachedCriteria);
    }

    public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria){
        if (pager == null){
            pager = new Pager();
        }
        Integer pageNumber = pager.getPageNumber();
        Integer pageSize = pager.getPageSize();
        String property = pager.getProperty();
        String keyword = pager.getKeyword();
        String orderBy = pager.getOrderBy();
        BaseEnum.OrderType orderType = pager.getOrderType();

        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        if (StringUtils.isNotEmpty(property) && StringUtils.isNotEmpty(keyword)){
            List<String> alias = new ArrayList<String>();
            if (property.contains(".")){
                String[] fields = StringUtils.split(property,".");
                for (int j=0;j<fields.length-1;j++){
                    if (!alias.contains(fields[j])){
                        criteria.createAlias(CommonUtil.getAliasPath(fields,j),fields[j]);
                        alias.add(fields[j]);
                    }
                }

                criteria.add(Restrictions.like(CommonUtil.getAliasPath(fields,fields.length-1,fields.length-2),"%"+keyword+"%"));
            }else{
                criteria.add(Restrictions.like(property,"%"+keyword+"%"));
            }
        }

        Integer totalCount = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
        criteria.setProjection(null);
        criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        if (pageNumber > 0){
            criteria.setFirstResult((pageNumber-1)*pageSize);
            criteria.setMaxResults(pageSize);
        }
        if (StringUtils.isNotEmpty(orderBy) && orderType != null){
            if (orderType == BaseEnum.OrderType.asc){
                criteria.addOrder(Order.asc(orderBy));
            }else{
                criteria.addOrder(Order.desc(orderBy));
            }
        }
        pager.setTotalCount(totalCount);
        pager.setList(criteria.list());
        return pager;
    }


    @Override
    public Pager findByPagerAndCompany(Pager pager, Users users, Company company, BaseEnum.StateEnum[] states) {
        if (pager == null) {
            pager = new Pager();
        }
        DetachedCriteria detachedCriteria = DetachedCriteria
                .forClass(entityClass);
        if (states != null && states.length > 0) {
            Criterion[] criterions = new Criterion[states.length];
            for (int i = 0; i < states.length; i++) {
                criterions[i] = Restrictions.eq("state", states[i]);
            }
            detachedCriteria.add(Restrictions.or(criterions));
        }
        if (users != null) {
            detachedCriteria.createAlias("creater", "creater");
            detachedCriteria.add(Restrictions.or(
                    Restrictions.eq("creater", users),
                    Restrictions.eq("creater.id", users.getId())));
        }
        if (company != null) {
            detachedCriteria.createAlias("company", "company");
            detachedCriteria.add(Restrictions.or(
                    Restrictions.eq("company", company),
                    Restrictions.eq("company.id", company.getId())));
        }
        return findByPager(pager, detachedCriteria);
    }

    @Override
    public Pager findByPagerAndLimit(Pager pager, Set<Users> usersSet, Company company, Map<String, Object> rmap) {
        if(pager==null){
            pager=new Pager();
        }
        List<String> aliasList = new ArrayList<>();
        DetachedCriteria detachedCriteria=DetachedCriteria.forClass(entityClass);
        if (company != null) {
            detachedCriteria.createAlias("company", "company");
            if(!aliasList.contains("company"))
                aliasList.add("company");
            detachedCriteria.add(Restrictions.or(
                    Restrictions.eq("company", company),
                    Restrictions.eq("company.id", company.getId())));
        } else {
            return pager;
        }
        String ids ="";
        if(usersSet!=null){
            for(Users users:usersSet){
                ids+="'"+users.getId()+"'"+',';
                // ids = ids + users.getId()+',';
            }
            ids = ids.substring(0,ids.length()-1);
            if(StringUtils.contains(StringUtils.lowerCase(entityClass.getName()),"proinfo")){
                detachedCriteria.add(Restrictions.or(
                        Restrictions.in("creater", usersSet),
                        Restrictions.in("chief", usersSet),
                        Restrictions.sqlRestriction("this_.id in (select sgu.id from ec_proinfo_group as sgu where sgu.userId in (" + ids + "))")));
            }else if(StringUtils.contains(StringUtils.lowerCase(entityClass.getName()),"comment")){
                detachedCriteria.add(Restrictions.eq("targetId",rmap.get("targetId").toString())).addOrder(Order.asc("createDate"));
            } else{
                detachedCriteria.createAlias("proInfo", "proInfo");
                if(!aliasList.contains("proInfo"))
                    aliasList.add("proInfo");
                detachedCriteria.add(Restrictions.or(
                        Restrictions.in("creater", usersSet),
                        Restrictions.in("proInfo.chief", usersSet),
                        Restrictions.sqlRestriction("this_.proInfo_id in (select f.id from ec_proinfo as f where f.id in (select sgu.id from ec_proinfo_group as sgu where sgu.userId in (" + ids + ")))")));
            }
        }
        return findByPager(pager,detachedCriteria,aliasList,null,null,null,rmap);
    }
}
























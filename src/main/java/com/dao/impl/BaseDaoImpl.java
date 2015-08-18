package com.dao.impl;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.dao.base.BaseDao;
import com.entity.base.Entity;
import com.utils.CommonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by dqf on 2015/8/17.
 */
@Repository
public abstract class BaseDaoImpl<T extends Entity, PK extends Serializable> implements BaseDao<T, PK> {
    private Class<T> entityClass = null;
    @Resource
    protected SessionFactory sessionFactory;

    public BaseDaoImpl() {
        Class c = this.getClass();
        Type type = c.getGenericSuperclass();
        if(type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType)type).getActualTypeArguments();
            this.entityClass = (Class)parameterizedType[0];
        }

    }

    protected Class<T> getEntityClass() {
        return this.entityClass;
    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public Object getObject(String className, PK id) {
        Assert.notNull(className, "className is required");
        Assert.notNull(id, "id is required");
        return this.getSession().get(className, id);
    }

    public T get(PK id) {
        Assert.notNull(id, "id is required");
        return (T) this.getSession().get(this.entityClass, id);
    }

    public T load(PK id) {
        Assert.notNull(id, "id is required");
        return (T) this.getSession().load(this.entityClass, id);
    }

    public List<T> get(PK[] ids) {
        Assert.notEmpty(ids, "ids must not be empty");
        String hql = "from " + this.entityClass.getName() + " model where model.id in(:ids)";
        return this.getSession().createQuery(hql).setParameterList("ids", ids).list();
    }

    public T get(String propertyName, Object value) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(value, "value is required");
        String hql = "from " + this.entityClass.getName() + " model where model." + propertyName + " = ?";
        return (T) this.getSession().createQuery(hql).setParameter(0, value).uniqueResult();
    }

    public List<T> getList(String propertyName, Object value) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        String hql;
        if(value == null) {
            hql = "from " + this.entityClass.getName() + " model where model." + propertyName + " is null";
            return this.getSession().createQuery(hql).list();
        } else {
            hql = "from " + this.entityClass.getName() + " model where model." + propertyName + " = ?";
            return this.getSession().createQuery(hql).setParameter(0, value).list();
        }
    }

    public List<T> getAll() {
        String hql = "from " + this.entityClass.getName();
        return this.getSession().createQuery(hql).list();
    }

    public Long getTotalCount() {
        String hql = "select count(*) from " + this.entityClass.getName();
        return (Long)this.getSession().createQuery(hql).uniqueResult();
    }

    public boolean isUnique(String propertyName, Object oldValue, Object newValue) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(newValue, "newValue is required");
        if(newValue != oldValue && !newValue.equals(oldValue)) {
            if(newValue instanceof String && oldValue != null && StringUtils.equalsIgnoreCase((String) oldValue, (String) newValue)) {
                return true;
            } else {
                Entity object = this.get(propertyName, newValue);
                return object == null;
            }
        } else {
            return true;
        }
    }

    public boolean isExist(String propertyName, Object value) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(value, "value is required");
        Entity object = this.get(propertyName, value);
        return object != null;
    }

    public PK save(T entity) {
        Assert.notNull(entity, "entity is required");
        return (PK) this.getSession().save(entity);
    }

    public void update(T entity) {
        Assert.notNull(entity, "entity is required");
        this.getSession().update(entity);
    }

    public void delete(T entity) {
        Assert.notNull(entity, "entity is required");
        this.getSession().delete(entity);
    }

    public void delete(PK id) {
        Assert.notNull(id, "id is required");
        Entity entity = this.load(id);
        this.getSession().delete(entity);
    }

    public void delete(PK[] ids) {
    }

    public void flush() {
    }

    public void clear() {
    }

    public void evict(Object object) {
    }

    public Pager findByPager(Pager pager) {
        return this.findByPager(pager, (DetachedCriteria)null, (List)null);
    }

    public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria, List<String> aliasList) {
        if(pager == null) {
            pager = new Pager();
        }

        if(detachedCriteria == null) {
            detachedCriteria = DetachedCriteria.forClass(this.entityClass);
        }

        if(aliasList == null) {
            aliasList = new ArrayList();
        }

        Integer pageNumber = pager.getPageNumber();
        Integer pageSize = pager.getPageSize();
        String property = pager.getProperty();
        String keyword = pager.getKeyword();
        String orderBy = pager.getOrderBy();
        BaseEnum.OrderType orderType = pager.getOrderType();
        Criteria criteria = detachedCriteria.getExecutableCriteria(this.getSession());
        if(StringUtils.isNotEmpty(property) && StringUtils.isNotEmpty(keyword)) {
            if(StringUtils.contains(property, ".")) {
                String[] totalCount = StringUtils.split(property, ".");

                for(int fields = 0; fields < totalCount.length - 1; ++fields) {
                    if(!((List)aliasList).contains(CommonUtil.getAlias(totalCount, fields))) {
                        criteria.createAlias(CommonUtil.getPath(totalCount, fields), CommonUtil.getAlias(totalCount, fields));
                        ((List)aliasList).add(CommonUtil.getAlias(totalCount, fields));
                    }
                }

                criteria.add(Restrictions.like(CommonUtil.getPath(totalCount, totalCount.length - 1), "%" + keyword + "%"));
            } else {
                criteria.add(Restrictions.like(property, "%" + keyword + "%"));
            }
        }

        Integer var14 = Integer.valueOf(Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString()));
        criteria.setProjection((Projection)null);
        criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        if(pageNumber.intValue() > 0) {
            criteria.setFirstResult((pageNumber.intValue() - 1) * pageSize.intValue());
            criteria.setMaxResults(pageSize.intValue());
        }

        if(StringUtils.isNotEmpty(orderBy) && orderType != null) {
            if(StringUtils.contains(orderBy, ".")) {
                String[] var15 = StringUtils.split(orderBy, ".");

                for(int j = 0; j < var15.length - 1; ++j) {
                    if(!((List)aliasList).contains(CommonUtil.getAlias(var15, j))) {
                        criteria.createAlias(CommonUtil.getPath(var15, j), CommonUtil.getAlias(var15, j));
                        ((List)aliasList).add(CommonUtil.getAlias(var15, j));
                    }
                }

                if(BaseEnum.OrderType.asc.equals(orderType)) {
                    criteria.addOrder(Order.asc(CommonUtil.getPath(var15, var15.length - 1)));
                } else {
                    criteria.addOrder(Order.desc(CommonUtil.getPath(var15, var15.length - 1)));
                }
            } else if(BaseEnum.OrderType.asc.equals(orderType)) {
                criteria.addOrder(Order.asc(orderBy));
            } else {
                criteria.addOrder(Order.desc(orderBy));
            }
        }

        pager.setTotalCount(var14);
        pager.setList(criteria.list());
        return pager;
    }

    public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria, List<String> aliasList, String searchDate, Date beginDate, Date endDate, Map<String, Object> params) {
        if(detachedCriteria == null) {
            detachedCriteria = DetachedCriteria.forClass(this.entityClass);
        }

        if(aliasList == null) {
            aliasList = new ArrayList();
        }

        if(StringUtils.isNotEmpty(searchDate) && (beginDate != null || endDate != null)) {
            if(StringUtils.contains(searchDate, ".")) {
                String[] i$ = StringUtils.split(searchDate, ".");

                for(int field = 0; field < i$.length - 1; ++field) {
                    if(!((List)aliasList).contains(CommonUtil.getAlias(i$, field))) {
                        detachedCriteria.createAlias(CommonUtil.getPath(i$, field), CommonUtil.getAlias(i$, field));
                        ((List)aliasList).add(CommonUtil.getAlias(i$, field));
                    }
                }

                if(beginDate != null) {
                    detachedCriteria.add(Restrictions.ge(CommonUtil.getPath(i$, i$.length - 1), beginDate));
                }

                if(endDate != null) {
                    detachedCriteria.add(Restrictions.lt(CommonUtil.getPath(i$, i$.length - 1), endDate));
                }
            } else {
                if(beginDate != null) {
                    detachedCriteria.add(Restrictions.ge(searchDate, beginDate));
                }

                if(endDate != null) {
                    detachedCriteria.add(Restrictions.lt(searchDate, endDate));
                }
            }
        }

        if(params != null && params.size() > 0) {
            Iterator var16 = params.keySet().iterator();

            while(var16.hasNext()) {
                String var17 = (String)var16.next();
                if(params.get(var17) != null) {
                    Object value = params.get(var17);
                    if(StringUtils.contains(var17, ".")) {
                        String[] var18 = StringUtils.split(var17, ".");

                        for(int var19 = 0; var19 < var18.length - 1; ++var19) {
                            if(!((List)aliasList).contains(CommonUtil.getAlias(var18, var19))) {
                                detachedCriteria.createAlias(CommonUtil.getPath(var18, var19), CommonUtil.getAlias(var18, var19));
                                ((List)aliasList).add(CommonUtil.getAlias(var18, var19));
                            }
                        }

                        if(value instanceof String) {
                            detachedCriteria.add(Restrictions.like(CommonUtil.getPath(var18, var18.length - 1), "%" + value + "%"));
                        } else if(!(value instanceof Object[])) {
                            if(value instanceof Entity) {
                                detachedCriteria.add(Restrictions.or(Restrictions.eq(CommonUtil.getPath(var18, var18.length - 1), value), Restrictions.eq(CommonUtil.getPath(var18, var18.length - 1) + ".id", ((Entity) value).getId())));
                            } else {
                                detachedCriteria.add(Restrictions.eq(CommonUtil.getPath(var18, var18.length - 1), value));
                            }
                        } else {
                            Object[] var20 = (Object[])((Object[])value);
                            if(var20 != null && var20.length > 0) {
                                Criterion[] var21 = new Criterion[var20.length];

                                for(int var22 = 0; var22 < var20.length; ++var22) {
                                    Object o1 = var20[var22];
                                    if(o1 instanceof Entity) {
                                        var21[var22] = Restrictions.or(Restrictions.eq(CommonUtil.getPath(var18, var18.length - 1), o1), Restrictions.eq(CommonUtil.getPath(var18, var18.length - 1) + ".id", ((Entity) o1).getId()));
                                    } else {
                                        var21[var22] = Restrictions.eq(CommonUtil.getPath(var18, var18.length - 1), o1);
                                    }
                                }

                                detachedCriteria.add(Restrictions.or(var21));
                            }
                        }
                    } else if(value instanceof String) {
                        detachedCriteria.add(Restrictions.like(var17, "%" + value + "%"));
                    } else if(!(value instanceof Object[])) {
                        if(value instanceof Entity) {
                            detachedCriteria.add(Restrictions.or(Restrictions.eq(var17, value), Restrictions.eq(var17 + ".id", ((Entity) value).getId())));
                        } else {
                            detachedCriteria.add(Restrictions.eq(var17, value));
                        }
                    } else {
                        Object[] values = (Object[])((Object[])value);
                        if(values != null && values.length > 0) {
                            Criterion[] criterions = new Criterion[values.length];

                            for(int i = 0; i < values.length; ++i) {
                                Object o = values[i];
                                if(o instanceof Entity) {
                                    criterions[i] = Restrictions.or(Restrictions.eq(var17, o), Restrictions.eq(var17 + ".id", ((Entity) o).getId()));
                                } else {
                                    criterions[i] = Restrictions.eq(var17, o);
                                }
                            }

                            detachedCriteria.add(Restrictions.or(criterions));
                        }
                    }
                }
            }
        }

        return this.findByPager(pager, detachedCriteria, (List)aliasList);
    }

    public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria, List<String> aliasList, String searchDate, Date beginDate, Date endDate, Map<String, Object> params, String orderBy, BaseEnum.OrderType orderType) {
        if(detachedCriteria == null) {
            detachedCriteria = DetachedCriteria.forClass(this.entityClass);
        }

        if(aliasList == null) {
            aliasList = new ArrayList();
        }

        if(StringUtils.isNotEmpty(searchDate) && (beginDate != null || endDate != null)) {
            if(StringUtils.contains(searchDate, ".")) {
                String[] i$ = StringUtils.split(searchDate, ".");

                for(int field = 0; field < i$.length - 1; ++field) {
                    if(!((List)aliasList).contains(CommonUtil.getAlias(i$, field))) {
                        detachedCriteria.createAlias(CommonUtil.getPath(i$, field), CommonUtil.getAlias(i$, field));
                        ((List)aliasList).add(CommonUtil.getAlias(i$, field));
                    }
                }

                if(beginDate != null) {
                    detachedCriteria.add(Restrictions.ge(CommonUtil.getPath(i$, i$.length - 1), beginDate));
                }

                if(endDate != null) {
                    detachedCriteria.add(Restrictions.lt(CommonUtil.getPath(i$, i$.length - 1), endDate));
                }
            } else {
                if(beginDate != null) {
                    detachedCriteria.add(Restrictions.ge(searchDate, beginDate));
                }

                if(endDate != null) {
                    detachedCriteria.add(Restrictions.lt(searchDate, endDate));
                }
            }
        }

        if(params != null && params.size() > 0) {
            Iterator var18 = params.keySet().iterator();

            while(var18.hasNext()) {
                String var19 = (String)var18.next();
                if(params.get(var19) != null) {
                    Object value = params.get(var19);
                    if(StringUtils.contains(var19, ".")) {
                        String[] var20 = StringUtils.split(var19, ".");

                        for(int var21 = 0; var21 < var20.length - 1; ++var21) {
                            if(!((List)aliasList).contains(CommonUtil.getAlias(var20, var21))) {
                                detachedCriteria.createAlias(CommonUtil.getPath(var20, var21), CommonUtil.getAlias(var20, var21));
                                ((List)aliasList).add(CommonUtil.getAlias(var20, var21));
                            }
                        }

                        if(value instanceof String) {
                            detachedCriteria.add(Restrictions.like(CommonUtil.getPath(var20, var20.length - 1), "%" + value + "%"));
                        } else if(!(value instanceof Object[])) {
                            if(value instanceof Entity) {
                                detachedCriteria.add(Restrictions.or(Restrictions.eq(CommonUtil.getPath(var20, var20.length - 1), value), Restrictions.eq(CommonUtil.getPath(var20, var20.length - 1) + ".id", ((Entity) value).getId())));
                            } else {
                                detachedCriteria.add(Restrictions.eq(CommonUtil.getPath(var20, var20.length - 1), value));
                            }
                        } else {
                            Object[] var22 = (Object[])((Object[])value);
                            if(var22 != null && var22.length > 0) {
                                Criterion[] var23 = new Criterion[var22.length];

                                for(int var24 = 0; var24 < var22.length; ++var24) {
                                    Object o1 = var22[var24];
                                    if(o1 instanceof Entity) {
                                        var23[var24] = Restrictions.or(Restrictions.eq(CommonUtil.getPath(var20, var20.length - 1), o1), Restrictions.eq(CommonUtil.getPath(var20, var20.length - 1) + ".id", ((Entity) o1).getId()));
                                    } else {
                                        var23[var24] = Restrictions.eq(CommonUtil.getPath(var20, var20.length - 1), o1);
                                    }
                                }

                                detachedCriteria.add(Restrictions.or(var23));
                            }
                        }
                    } else if(value instanceof String) {
                        detachedCriteria.add(Restrictions.like(var19, "%" + value + "%"));
                    } else if(!(value instanceof Object[])) {
                        if(value instanceof Entity) {
                            detachedCriteria.add(Restrictions.or(Restrictions.eq(var19, value), Restrictions.eq(var19 + ".id", ((Entity) value).getId())));
                        } else {
                            detachedCriteria.add(Restrictions.eq(var19, value));
                        }
                    } else {
                        Object[] values = (Object[])((Object[])value);
                        if(values != null && values.length > 0) {
                            Criterion[] criterions = new Criterion[values.length];

                            for(int i = 0; i < values.length; ++i) {
                                Object o = values[i];
                                if(o instanceof Entity) {
                                    criterions[i] = Restrictions.or(Restrictions.eq(var19, o), Restrictions.eq(var19 + ".id", ((Entity) o).getId()));
                                } else {
                                    criterions[i] = Restrictions.eq(var19, o);
                                }
                            }

                            detachedCriteria.add(Restrictions.or(criterions));
                        }
                    }
                }
            }
        }

        if(StringUtils.isNotEmpty(orderBy) && orderType != null) {
            pager.setOrderBy(orderBy);
            pager.setOrderType(orderType);
        }

        return this.findByPager(pager, detachedCriteria, (List)aliasList);
    }

    public List<T> getList(DetachedCriteria detachedCriteria, List<String> aliasList, String searchDate, Date beginDate, Date endDate, Map<String, Object> params, String orderBy, BaseEnum.OrderType orderType) {
        if(detachedCriteria == null) {
            detachedCriteria = DetachedCriteria.forClass(this.entityClass);
        }

        if(aliasList == null) {
            aliasList = new ArrayList();
        }

        Criteria criteria = detachedCriteria.getExecutableCriteria(this.getSession());
        String[] fields;
        int j;
        if(StringUtils.isNotEmpty(searchDate) && (beginDate != null || endDate != null)) {
            if(StringUtils.contains(searchDate, ".")) {
                fields = StringUtils.split(searchDate, ".");

                for(j = 0; j < fields.length - 1; ++j) {
                    if(!((List)aliasList).contains(CommonUtil.getAlias(fields, j))) {
                        detachedCriteria.createAlias(CommonUtil.getPath(fields, j), CommonUtil.getAlias(fields, j));
                        ((List)aliasList).add(CommonUtil.getAlias(fields, j));
                    }
                }

                if(beginDate != null) {
                    detachedCriteria.add(Restrictions.ge(CommonUtil.getPath(fields, fields.length - 1), beginDate));
                }

                if(endDate != null) {
                    detachedCriteria.add(Restrictions.lt(CommonUtil.getPath(fields, fields.length - 1), endDate));
                }
            } else {
                if(beginDate != null) {
                    detachedCriteria.add(Restrictions.ge(searchDate, beginDate));
                }

                if(endDate != null) {
                    detachedCriteria.add(Restrictions.lt(searchDate, endDate));
                }
            }
        }

        if(params != null && params.size() > 0) {
            Iterator var18 = params.keySet().iterator();

            while(var18.hasNext()) {
                String var19 = (String)var18.next();
                if(params.get(var19) != null) {
                    Object value = params.get(var19);
                    if(StringUtils.contains(var19, ".")) {
                        String[] var20 = StringUtils.split(var19, ".");

                        for(int var21 = 0; var21 < var20.length - 1; ++var21) {
                            if(!((List)aliasList).contains(CommonUtil.getAlias(var20, var21))) {
                                criteria.createAlias(CommonUtil.getPath(var20, var21), CommonUtil.getAlias(var20, var21));
                                ((List)aliasList).add(CommonUtil.getAlias(var20, var21));
                            }
                        }

                        if(value instanceof String) {
                            criteria.add(Restrictions.like(CommonUtil.getPath(var20, var20.length - 1), "%" + value + "%"));
                        } else if(!(value instanceof Object[])) {
                            if(value instanceof Entity) {
                                criteria.add(Restrictions.or(Restrictions.eq(CommonUtil.getPath(var20, var20.length - 1), value), Restrictions.eq(CommonUtil.getPath(var20, var20.length - 1) + ".id", ((Entity) value).getId())));
                            } else {
                                criteria.add(Restrictions.eq(CommonUtil.getPath(var20, var20.length - 1), value));
                            }
                        } else {
                            Object[] var22 = (Object[])((Object[])value);
                            if(var22 != null && var22.length > 0) {
                                Criterion[] var23 = new Criterion[var22.length];

                                for(int var24 = 0; var24 < var22.length; ++var24) {
                                    Object o1 = var22[var24];
                                    if(o1 instanceof Entity) {
                                        var23[var24] = Restrictions.or(Restrictions.eq(CommonUtil.getPath(var20, var20.length - 1), o1), Restrictions.eq(CommonUtil.getPath(var20, var20.length - 1) + ".id", ((Entity) o1).getId()));
                                    } else {
                                        var23[var24] = Restrictions.eq(CommonUtil.getPath(var20, var20.length - 1), o1);
                                    }
                                }

                                criteria.add(Restrictions.or(var23));
                            }
                        }
                    } else if(value instanceof String) {
                        criteria.add(Restrictions.like(var19, "%" + value + "%"));
                    } else if(!(value instanceof Object[])) {
                        if(value instanceof Entity) {
                            criteria.add(Restrictions.or(Restrictions.eq(var19, value), Restrictions.eq(var19 + ".id", ((Entity) value).getId())));
                        } else {
                            criteria.add(Restrictions.eq(var19, value));
                        }
                    } else {
                        Object[] values = (Object[])((Object[])value);
                        if(values != null && values.length > 0) {
                            Criterion[] criterions = new Criterion[values.length];

                            for(int i = 0; i < values.length; ++i) {
                                Object o = values[i];
                                if(o instanceof Entity) {
                                    criterions[i] = Restrictions.or(Restrictions.eq(var19, o), Restrictions.eq(var19 + ".id", ((Entity) o).getId()));
                                } else {
                                    criterions[i] = Restrictions.eq(var19, o);
                                }
                            }

                            criteria.add(Restrictions.or(criterions));
                        }
                    }
                }
            }
        }

        if(StringUtils.isNotEmpty(orderBy) && orderType != null) {
            if(StringUtils.contains(orderBy, ".")) {
                fields = StringUtils.split(orderBy, ".");

                for(j = 0; j < fields.length - 1; ++j) {
                    if(!((List)aliasList).contains(CommonUtil.getAlias(fields, j))) {
                        criteria.createAlias(CommonUtil.getPath(fields, j), CommonUtil.getAlias(fields, j));
                        ((List)aliasList).add(CommonUtil.getAlias(fields, j));
                    }
                }

                if(BaseEnum.OrderType.asc.equals(orderType)) {
                    criteria.addOrder(Order.asc(CommonUtil.getPath(fields, fields.length - 1)));
                } else {
                    criteria.addOrder(Order.desc(CommonUtil.getPath(fields, fields.length - 1)));
                }
            } else if(BaseEnum.OrderType.asc.equals(orderType)) {
                criteria.addOrder(Order.asc(orderBy));
            } else {
                criteria.addOrder(Order.desc(orderBy));
            }
        }

        return criteria.list();
    }

    public List<T> getAll(String hql) {
        return this.sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    public Query getQuery(String hql) {
        return this.sessionFactory.getCurrentSession().createQuery(hql);
    }

    public Object getUniqueResult(String hql) {
        return this.sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
    }

    public Object getUniqueResult(String hql, Map<String, Object> params) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        if(params != null && params.size() > 0) {
            Iterator i$ = params.keySet().iterator();

            while(i$.hasNext()) {
                String key = (String)i$.next();
                Object value = params.get(key);
                if(value instanceof Collection) {
                    query.setParameterList(key, (Collection)value);
                } else if(value instanceof Object[]) {
                    query.setParameterList(key, (Object[])((Object[])value));
                } else {
                    query.setParameter(key, value);
                }
            }
        }

        return query.uniqueResult();
    }

    public List<T> getList(String hql, Map<String, Object> params) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        if(params != null && params.size() > 0) {
            Iterator i$ = params.keySet().iterator();

            while(i$.hasNext()) {
                String key = (String)i$.next();
                Object value = params.get(key);
                if(value instanceof Collection) {
                    query.setParameterList(key, (Collection)value);
                } else if(value instanceof Object[]) {
                    query.setParameterList(key, (Object[])((Object[])value));
                } else {
                    query.setParameter(key, value);
                }
            }
        }

        return query.list();
    }

    public List<Map<String, Object>> getMapedList(String hql, Map<String, Object> params) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        if(params != null && params.size() > 0) {
            Iterator i$ = params.keySet().iterator();

            while(i$.hasNext()) {
                String key = (String)i$.next();
                Object value = params.get(key);
                if(value instanceof Collection) {
                    query.setParameterList(key, (Collection)value);
                } else if(value instanceof Object[]) {
                    query.setParameterList(key, (Object[])((Object[])value));
                } else {
                    query.setParameter(key, value);
                }
            }
        }

        return query.list();
    }

    public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria, List<String> aliasList, String search, String filters, String sidx, String sord) {
        if(pager == null) {
            pager = new Pager();
        }

        if(detachedCriteria == null) {
            detachedCriteria = DetachedCriteria.forClass(this.entityClass);
        }

        if(aliasList == null) {
            aliasList = new ArrayList();
        }

        if(StringUtils.isNotEmpty(search)) {
            boolean fields = Boolean.parseBoolean(search);
            if(fields && StringUtils.isNotEmpty(filters)) {
                JSONObject j = JSONObject.fromObject(filters);
                if(StringUtils.equals(j.getString("groupOp"), "AND")) {
                    JSONArray rulesJsons = j.getJSONArray("rules");

                    for(int i = 0; i < rulesJsons.size(); ++i) {
                        JSONObject rule = rulesJsons.getJSONObject(i);
                        if(rule.has("field") && StringUtils.isNotEmpty(rule.getString("field")) && rule.has("data") && StringUtils.isNotEmpty(rule.getString("data"))) {
                            String field = rule.getString("field");
                            if(field.contains("_")) {
                                String[] fields1 = field.split("_");

                                for(int j1 = 0; j1 < fields1.length - 1; ++j1) {
                                    if(!((List)aliasList).contains(fields1[j1])) {
                                        detachedCriteria.createAlias(CommonUtil.getPath(fields1, j1), CommonUtil.getAlias(fields1, j1));
                                        ((List)aliasList).add(CommonUtil.getAlias(fields1, j1));
                                    }
                                }

                                detachedCriteria.add(Restrictions.like(CommonUtil.getPath(fields1, fields1.length - 1), CommonUtil.getData(field, rule.getString("data"))));
                            } else {
                                detachedCriteria.add(Restrictions.like(field, CommonUtil.getData(field, rule.getString("data"))));
                            }
                        }
                    }
                }
            }
        }

        if(StringUtils.isNotEmpty(sidx) && StringUtils.isNotEmpty(sord)) {
            if(sidx.contains("_")) {
                String[] var16 = sidx.split("_");

                for(int var17 = 0; var17 < var16.length - 1; ++var17) {
                    if(!((List)aliasList).contains(var16[var17])) {
                        detachedCriteria.createAlias(CommonUtil.getPath(var16, var17), CommonUtil.getAlias(var16, var17));
                        ((List)aliasList).add(CommonUtil.getAlias(var16, var17));
                    }
                }

                if(StringUtils.equals("asc", sord)) {
                    detachedCriteria.addOrder(Order.asc(CommonUtil.getPath(var16, var16.length - 1)));
                } else {
                    detachedCriteria.addOrder(Order.desc(CommonUtil.getPath(var16, var16.length - 1)));
                }
            } else if(StringUtils.equals("asc", sord)) {
                detachedCriteria.addOrder(Order.asc(sidx));
            } else {
                detachedCriteria.addOrder(Order.desc(sidx));
            }
        }

        return this.findByPager(pager, detachedCriteria, (List)aliasList);
    }

    public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria) {
        if(pager == null) {
            pager = new Pager();
        }

        Integer pageNumber = pager.getPageNumber();
        Integer pageSize = pager.getPageSize();
        String property = pager.getProperty();
        String keyword = pager.getKeyword();
        String orderBy = pager.getOrderBy();
        BaseEnum.OrderType orderType = pager.getOrderType();
        Criteria criteria = detachedCriteria.getExecutableCriteria(this.getSession());
        if(StringUtils.isNotEmpty(property) && StringUtils.isNotEmpty(keyword)) {
            ArrayList totalCount = new ArrayList();
            if(property.contains(".")) {
                String[] fields = StringUtils.split(property, ".");

                for(int j = 0; j < fields.length - 1; ++j) {
                    if(!totalCount.contains(fields[j])) {
                        criteria.createAlias(CommonUtil.getAliasPath(fields, j), fields[j]);
                        totalCount.add(fields[j]);
                    }
                }

                criteria.add(Restrictions.like(CommonUtil.getAliasPath(fields, fields.length - 1, fields.length - 2), "%" + keyword + "%"));
            } else {
                criteria.add(Restrictions.like(property, "%" + keyword + "%"));
            }
        }

        Integer var13 = Integer.valueOf(Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString()));
        criteria.setProjection((Projection)null);
        criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        if(pageNumber.intValue() > 0) {
            criteria.setFirstResult((pageNumber.intValue() - 1) * pageSize.intValue());
            criteria.setMaxResults(pageSize.intValue());
        }

        if(StringUtils.isNotEmpty(orderBy) && orderType != null) {
            if(orderType == BaseEnum.OrderType.asc) {
                criteria.addOrder(Order.asc(orderBy));
            } else {
                criteria.addOrder(Order.desc(orderBy));
            }
        }

        pager.setTotalCount(var13);
        pager.setList(criteria.list());
        return pager;
    }
}
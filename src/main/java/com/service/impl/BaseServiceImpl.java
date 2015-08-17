package com.service.impl;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.dao.BaseDao;
import com.entity.base.Entity;
import com.service.BaseService;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dqf on 2015/8/17.
 */
@Service
public abstract class BaseServiceImpl<T extends Entity, PK extends Serializable> implements BaseService<T, PK> {
    private BaseDao<T, PK> baseDao;

    public BaseServiceImpl() {
    }

    public abstract BaseDao<T, PK> getBaseDao();

    public void setBaseDao(BaseDao<T, PK> baseDao) {
        this.baseDao = baseDao;
    }

    public Object getObject(String className, PK id) {
        return this.getBaseDao().getObject(className, id);
    }

    public T get(PK id) {
        return this.getBaseDao().get(id);
    }

    public T load(PK id) {
        return this.getBaseDao().load(id);
    }

    public List<T> get(PK[] ids) {
        return this.getBaseDao().get(ids);
    }

    public T get(String propertyName, Object value) {
        return this.getBaseDao().get(propertyName, value);
    }

    public List<T> getList(String propertyName, Object value) {
        return this.getBaseDao().getList(propertyName, value);
    }

    public List<T> getAll() {
        return this.getBaseDao().getAll();
    }

    public Query getQuery(String hql) {
        return this.getBaseDao().getQuery(hql);
    }

    public Object getUniqueResult(String hql) {
        return this.getBaseDao().getUniqueResult(hql);
    }

    public Long getTotalCount() {
        return this.getBaseDao().getTotalCount();
    }

    public boolean isUnique(String propertyName, Object oldValue, Object newValue) {
        return this.getBaseDao().isUnique(propertyName, oldValue, newValue);
    }

    public boolean isExist(String propertyName, Object value) {
        return this.getBaseDao().isExist(propertyName, value);
    }

    public PK save(T entity) {
        return this.getBaseDao().save(entity);
    }

    public void update(T entity) {
        this.getBaseDao().update(entity);
    }

    public void delete(T entity) {
        this.getBaseDao().delete(entity);
    }

    public void delete(PK id) {
        this.getBaseDao().delete(id);
    }

    public void delete(PK[] ids) {
        this.getBaseDao().delete(ids);
    }

    public void flush() {
        this.getBaseDao().flush();
    }

    public void clear() {
        this.getBaseDao().clear();
    }

    public void evict(Object object) {
        this.getBaseDao().evict(object);
    }

    public Pager findByPager(Pager pager) {
        return this.getBaseDao().findByPager(pager);
    }

    public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria, List<String> aliasList) {
        return this.getBaseDao().findByPager(pager, detachedCriteria, aliasList);
    }

    public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria, List<String> aliasList, String searchDate, Date beginDate, Date endDate, Map<String, Object> params) {
        return this.getBaseDao().findByPager(pager, detachedCriteria, aliasList, searchDate, beginDate, endDate, params);
    }

    public Pager findByPager(Pager pager, String searchDate, Date beginDate, Date endDate, Map<String, Object> params) {
        return this.findByPager(pager, (DetachedCriteria)null, (List)null, (String)searchDate, (Date)beginDate, (Date)endDate, (Map)params);
    }

    public Pager findByPager(Pager pager, Map<String, Object> params) {
        return this.findByPager(pager, (String)null, (Date)null, (Date)null, (Map)params);
    }

    public List<T> getList(DetachedCriteria detachedCriteria, List<String> aliasList, String searchDate, Date beginDate, Date endDate, Map<String, Object> params, String orderBy, BaseEnum.OrderType orderType) {
        return this.getBaseDao().getList(detachedCriteria, aliasList, searchDate, beginDate, endDate, params, orderBy, orderType);
    }

    public List<T> getList(String searchDate, Date beginDate, Date endDate, Map<String, Object> params, String orderBy, BaseEnum.OrderType orderType) {
        return this.getList((DetachedCriteria)null, (List)null, searchDate, beginDate, endDate, params, orderBy, orderType);
    }

    public List<T> getList(Map<String, Object> params, String orderBy, BaseEnum.OrderType orderType) {
        return this.getList("", (Date)null, (Date)null, params, orderBy, orderType);
    }

    public List<T> getList(Map<String, Object> params) {
        return this.getList(params, "createDate", BaseEnum.OrderType.asc);
    }

    public List<T> getAll(String hql) {
        return this.getBaseDao().getAll(hql);
    }

    public List<T> getList(String hql, Map<String, Object> params) {
        return this.getBaseDao().getList(hql, params);
    }

    public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria, List<String> aliasList, String search, String filters, String sidx, String sord) {
        return this.getBaseDao().findByPager(pager, detachedCriteria, aliasList, search, filters, sidx, sord);
    }

    public Pager findByPager(Pager pager, String search, String filters, String sidx, String sord) {
        return this.findByPager(pager, (DetachedCriteria)null, (List)null, (String)search, (String)filters, (String)sidx, (String)sord);
    }

    public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria, List<String> aliasList, String searchDate, Date beginDate, Date endDate, Map<String, Object> params, String orderBy, BaseEnum.OrderType orderType) {
        return this.getBaseDao().findByPager(pager, detachedCriteria, aliasList, searchDate, beginDate, endDate, params, orderBy, orderType);
    }

    public Pager findByPager(Pager pager, Map<String, Object> params, String orderBy, BaseEnum.OrderType orderType) {
        return this.findByPager(pager, (String)null, (Date)null, (Date)null, (Map)params, (String)orderBy, (BaseEnum.OrderType)orderType);
    }

    public Pager findByPager(Pager pager, String searchDate, Date beginDate, Date endDate, Map<String, Object> params, String orderBy, BaseEnum.OrderType orderType) {
        return this.getBaseDao().findByPager(pager, (DetachedCriteria)null, (List)null, searchDate, beginDate, endDate, params, orderBy, orderType);
    }
}

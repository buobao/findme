package com.dao.impl;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.dao.base.MongoBaseDao;
import com.entity.base.MongoEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by dqf on 2015/8/17.
 */
@Repository
public abstract class MongoBaseDaoImpl<T extends MongoEntity, PK extends Serializable> implements MongoBaseDao<T, PK> {
    private static final int DEFAULT_SKIP = 0;
    private static final int DEFAULT_LIMIT = 200;
    private Class<T> entityClass;
    @Resource
    protected MongoTemplate mongoTemplate;

    public MongoBaseDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public MongoBaseDaoImpl() {
        this.entityClass = null;
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

    public List<T> find(Query query) {
        return this.mongoTemplate.find(query, this.entityClass);
    }

    public T findOne(Query query) {
        return (T) this.mongoTemplate.findOne(query, this.entityClass);
    }

    public void update(Query query, Update update) {
        this.mongoTemplate.findAndModify(query, update, this.entityClass);
    }

    public T save(T entity) {
        this.mongoTemplate.insert(entity);
        return entity;
    }

    public T findById(String id) {
        System.out.println(this.entityClass);
        return (T) this.mongoTemplate.findById(id, this.entityClass);
    }

    public T findById(String id, String collectionName) {
        return (T) this.mongoTemplate.findById(id, this.entityClass, collectionName);
    }

    public Pager findPage(Pager pager, Query query) {
        long count = this.count(query);
        int i = (int)count;
        pager.setTotalCount(Integer.valueOf(i));
        int pageNumber = pager.getPageNumber().intValue();
        int pageSize = pager.getPageSize().intValue();
        if(StringUtils.isNotEmpty(pager.getOrderBy())) {
            query.with(new Sort(BaseEnum.OrderType.asc.equals(pager.getOrderBy())? Sort.Direction.ASC: Sort.Direction.DESC, new String[]{pager.getOrderBy()}));
        }

        query.skip((pageNumber - 1) * pageSize).limit(pageSize);
        List rows = this.find(query);
        pager.setList(rows);
        return pager;
    }

    public long count(Query query) {
        return this.mongoTemplate.count(query, this.entityClass);
    }
}

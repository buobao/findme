package com.service.impl;

import com.bean.Pager;
import com.dao.base.MongoBaseDao;
import com.entity.base.MongoEntity;
import com.service.MongoBaseService;
import com.service.common.ResultService;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dqf on 2015/8/17.
 */
@Service
public abstract class MongoBaseServiceImpl<T extends MongoEntity, PK extends Serializable> implements MongoBaseService<T, PK> {
    private MongoBaseDao<T, PK> mongoBaseDao;
    @Resource
    protected ResultService resultService;

    public MongoBaseServiceImpl() {
    }

    public abstract MongoBaseDao<T, PK> getMongoBaseDao();

    public void setMongoBaseDao(MongoBaseDao<T, PK> mongoBaseDao) {
        this.mongoBaseDao = mongoBaseDao;
    }

    public List<T> find(Query query) {
        return this.getMongoBaseDao().find(query);
    }

    public T findOne(Query query) {
        return this.getMongoBaseDao().findOne(query);
    }

    public void update(Query query, Update update) {
        this.getMongoBaseDao().update(query, update);
    }

    public T save(T entity) {
        return this.getMongoBaseDao().save(entity);
    }

    public T findById(String id) {
        return this.getMongoBaseDao().findById(id);
    }

    public T findById(String id, String collectionName) {
        return this.getMongoBaseDao().findById(id, collectionName);
    }

    public Pager findPage(Pager pager, Query query) {
        return this.getMongoBaseDao().findPage(pager, query);
    }

    public long count(Query query) {
        return this.getMongoBaseDao().count(query);
    }
}
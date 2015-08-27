package com.dao.base;

import com.entity.base.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dqf on 2015/8/25.
 */
public interface BaseCacheDao<E extends Entity, K extends Serializable> {
    /**
     * 新增
     * @param entity
     * @return
     */
    boolean add(E entity);

    /**
     * 批量新增
     * @param list
     * @return
     */
    boolean add(List<E> list);

    /**
     * 删除
     * @param entity
     */
    void delete(E entity);

    /**
     * 批量删除
     * @param entitys
     */
    void delete(List<E> entitys);

    /**
     * 修改
     * @param entity
     * @return
     */
    boolean update(E entity);

    /**
     * 获取对象
     * @param key
     * @return
     */
    E get(K key);
}

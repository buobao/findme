package com.dao.impl.base;

import com.dao.Jedis.AbstractBaseRedisDao;
import com.dao.base.BaseCacheDao;
import com.entity.base.Entity;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dqf on 2015/8/25.
 */
public class BaseCacheDaoImpl<E extends Entity, K extends Serializable> extends AbstractBaseRedisDao<E, K> implements BaseCacheDao<E, K>{
    /**
     * 新增
     * @param entity
     * @return
     */
    @Override
    public boolean add(final E entity){
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String > serializer = getRedisSerializer();
                byte[] key = serializer.serialize(entity.getId());
                byte[] name = serializer.serialize(entity.getClass().getName());
                return connection.setNX(key,name);
            }
        });
        return result;
    }

    /**
     * 批量新增
     * @param list
     * @return
     */
    @Override
    public boolean add(List<E> list){
        Assert.notEmpty(list);
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                for (E e:list){
                    byte[] key = serializer.serialize(e.getId());
                    byte[] name = serializer.serialize(e.getClass().getName());
                    connection.setNX(key, name);
                }
                return true;
            }
        },false,true);
        return result;
    }

    /**
     * 删除
     * @param entity
     */
    @Override
    public void delete(E entity){
        List<E> list = new ArrayList<E>();
        list.add(entity);
        delete(list);
    }

    /**
     * 批量删除
     * @param entitys
     */
    @Override
    public void delete(List<E> entitys){
        redisTemplate.delete(entitys);
    }

    /**
     * 修改
     * @param entity
     * @return
     */
    @Override
    public boolean update(E entity){
        K key = (K)entity.getId();
        if (get(key) == null){
            throw new NullPointerException("Data is not exist,key="+key);
        }
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(entity.getId());
                byte[] name = serializer.serialize(entity.getClass().getName());
                connection.set(key, name);
                return true;
            }
        });
        return result;
    }

    /**
     * 获取对象
     * @param id
     * @return
     */
    @Override
    public E get(final K id) {
        Entity result = redisTemplate.execute(new RedisCallback<Entity>() {
            @Override
            public Entity doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(id.toString());
                byte[] value = connection.get(key);
                if (value == null){
                    return null;
                }
                String name = serializer.deserialize(value);
                Entity entity = new Entity();
                entity.setId(key.toString());
                return new Entity();
            }
        });
        return (E)result;
    }
}


























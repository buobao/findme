package com.dao.Jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Created by dqf on 2015/8/25.
 */
public abstract class AbstractBaseRedisDao<E,K> {
    @Autowired
    protected RedisTemplate<E,K> redisTemplate;

    public void setRedisTemplate(RedisTemplate<E,K> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    protected RedisSerializer<String> getRedisSerializer(){
        return redisTemplate.getStringSerializer();
    }
}

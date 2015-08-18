package com.utils.db;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * Created by dqf on 2015/8/13.
 */
public class JedisUtil {
    private JedisUtil(){}
    private static JedisPool pool;
    static{
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        if(bundle == null){
            throw  new IllegalArgumentException("[application.properties] is not found!");
        }
        //System.out.println("maxTotal:"+maxTotal);
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(bundle.getString("redis.pool.maxTotal")));
        config.setMaxIdle(Integer.parseInt(bundle.getString("redis.pool.maxIdle")));
        config.setMaxWaitMillis(Integer.parseInt(bundle.getString("redis.pool.maxWait")));
        config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
        config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
        pool = new JedisPool(config,bundle.getString("redis.host"), Integer.parseInt(bundle.getString("redis.port")));
    }

    public static Jedis getJedis(){
        Jedis jedis = pool.getResource();
        return jedis;
    }

    public static void returnJedis(Jedis jedis){
        pool.returnResource(jedis);
    }
}


























package com;

import com.utils.db.JedisUtil;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.OutputCapture;
import org.springframework.data.redis.RedisConnectionFailureException;
import redis.clients.jedis.Jedis;

/**
 * Created by dqf on 2015/8/12.
 */
public class RedisTests {
    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void testDefaultSetting() throws Exception{
        Jedis jedis = JedisUtil.getJedis();
        jedis.set("springBootTest","This is redis test string!");
        System.out.println("Get from Redis: value->"+jedis.get("springBootTest"));
        JedisUtil.returnJedis(jedis);
        jedis = JedisUtil.getJedis();
        jedis.set("springBootTest","This is redis test string2!");
        System.out.println("Get from Redis: value->" + jedis.get("springBootTest"));
        JedisUtil.returnJedis(jedis);
    }

    private boolean redisServerRunning(Throwable ex){
        System.out.println(ex.getMessage());
        if (ex instanceof RedisConnectionFailureException){
            return false;
        }
        return (ex.getCause() == null || redisServerRunning(ex.getCause()));
    }
}

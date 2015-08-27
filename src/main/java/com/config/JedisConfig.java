package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by dqf on 2015/8/25.
 */
@Configuration
public class JedisConfig {
    @Value("${redis.pool.maxIdle}")
    private String maxIdle;
    @Value("${redis.pool.maxWait}")
    private String maxWait;
    @Value("${redis.pool.testOnBorrow}")
    private String testOnBorrow;
    @Value("${redis.pool.testOnReturn}")
    private String testOnReturn;
    @Value("${redis.pool.maxTotal}")
    private String maxTotal;

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private String port;
    @Value("${redis.password}")
    private String password;

    @Bean
    public JedisPoolConfig poolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.parseInt(maxIdle));
        config.setMaxWaitMillis(Integer.parseInt(maxWait));
        config.setTestOnBorrow(Boolean.parseBoolean(testOnBorrow));
        config.setTestOnReturn(Boolean.parseBoolean(testOnReturn));
        config.setMaxTotal(Integer.parseInt(maxTotal));
        return config;
    }

    @Bean
    public JedisConnectionFactory connectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setPoolConfig(poolConfig());
        factory.setHostName(host);
        factory.setPort(Integer.parseInt(port));
        factory.setPassword(password);
        return factory;
    }

    @Bean
    public StringRedisTemplate redisTemplate(){
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory());
        return template;
    }
}

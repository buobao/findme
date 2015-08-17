package com.dao.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by dqf on 2015/8/17.
 */
public class SimpleJdbcTemplate {
    protected final Log logger = LogFactory.getLog(this.getClass());
    protected NamedParameterJdbcTemplate njdbcTemplate;

    public SimpleJdbcTemplate(DataSource dataSource) {
        this.njdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List find(String sql, Class clazz, Map parameters) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            Assert.notNull(clazz, "集合中对象类型不能为空!");
            return parameters != null?this.njdbcTemplate.queryForList(sql, parameters, clazz):this.njdbcTemplate.queryForList(sql, parameters);
        } catch (Exception var5) {
            return null;
        }
    }

    public Object findForObject(String sql, Class clazz, Map parameters) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            Assert.notNull(clazz, "集合中对象类型不能为空!");
            return parameters != null?this.njdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(clazz)):null;
        } catch (Exception var5) {
            return null;
        }
    }

    public long findForLong(String sql, Map parameters) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            return parameters != null?((Long)this.njdbcTemplate.queryForObject(sql, parameters, Long.class)).longValue():0L;
        } catch (Exception var4) {
            return 0L;
        }
    }

    public int executeForObject(String sql, Object bean) {
        Assert.hasText(sql, "sql语句不正确!");
        return bean != null?this.njdbcTemplate.update(sql, this.paramBeanMapper(bean)):0;
    }

    public int executeForMap(String sql, Map parameters) {
        Assert.hasText(sql, "sql语句不正确!");
        return parameters != null?this.njdbcTemplate.update(sql, parameters):0;
    }

    protected ParameterizedBeanPropertyRowMapper resultBeanMapper(Class clazz) {
        return ParameterizedBeanPropertyRowMapper.newInstance(clazz);
    }

    protected BeanPropertySqlParameterSource paramBeanMapper(Object object) {
        return new BeanPropertySqlParameterSource(object);
    }

}

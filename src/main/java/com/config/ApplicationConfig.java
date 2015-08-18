package com.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.entity.interceptor.EntityInterceptor;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.jms.Queue;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by dqf on 2015/8/12.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(value = "com/entity/interceptor/*")
public class ApplicationConfig {
    @Autowired
    private Environment _env;         //配置文件信息获取
    @Autowired
    private DataSource _dataSource;
    @Autowired
    private LocalContainerEntityManagerFactoryBean _entityManagerFactory;

    @Autowired
    EntityInterceptor entityInterceptor;

    /**
     * activeMQ
     * */
    @Bean
    public Queue queue(){
        return new ActiveMQQueue("sample.queue");
    }

    @Bean
    public SecurityProperties securityProperties(){
        SecurityProperties security = new SecurityProperties();

        security.getBasic().setPath("");     // empty so home page is unsecured
        return security;
    }


    /**
     * 数据源,alibaba pool
     */
    @Bean
    public DruidDataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(_env.getProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(_env.getProperty("spring.datasource.url"));
        dataSource.setUsername(_env.getProperty("spring.datasource.username"));
        dataSource.setPassword(_env.getProperty("spring.datasource.password"));

        dataSource.setInitialSize(0);
        dataSource.setMaxActive(20);
        dataSource.setMaxIdle(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery(_env.getProperty("validationQuery"));
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);

        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(25200000);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(1800);
        dataSource.setLogAbandoned(true);
        dataSource.setFilters("mergeStat");

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();

        factory.setEntityInterceptor(entityInterceptor);
        factory.setDataSource(dataSource());
        Properties properties = new Properties();
        properties.put("hibernate.dialect",_env.getProperty("hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto",_env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql",_env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql",_env.getProperty("hibernate.format_sql"));
        properties.put("hibernate.current_session_context_class",_env.getProperty("hibernate.current_session_context_class"));
        factory.setHibernateProperties(properties);
        factory.setPackagesToScan("com.entity.sys","com.entity.core");   //可以设置多个

        return factory;
    }

    @Bean(autowire = Autowire.NO)
    public HibernateTransactionManager transactionManager() throws SQLException {
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(sessionFactory().getObject());
        return manager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException {
        JdbcTemplate jdbc = new JdbcTemplate();
        jdbc.setDataSource(dataSource());
        return jdbc;
    }

}

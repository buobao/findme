package com.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.entity.interceptor.EntityInterceptor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by dqf on 2015/8/17.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(value = "com/entity/interceptor/*")
public class DatabaseConfig {
    @Autowired
    private Environment _env;         //配置文件信息获取
    @Autowired
    private DataSource _dataSource;
    @Autowired
    private LocalContainerEntityManagerFactoryBean _entityManagerFactory;

    @Autowired
    EntityInterceptor entityInterceptor;
    // ==============
    // PUBLIC METHODS
    // ==============

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
        factory.setPackagesToScan("com.entity");   //可以设置多个

        return factory;
    }

    @Bean(autowire = Autowire.NO)
    public HibernateTransactionManager transactionManager() throws SQLException {
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setBeanFactory((BeanFactory) sessionFactory());
        return manager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException {
        JdbcTemplate jdbc = new JdbcTemplate();
        jdbc.setDataSource(dataSource());
        return jdbc;
    }

//    @Bean
//    public DruidStatInterceptor druidStatInterceptor(){
//        DruidStatInterceptor interceptor = new DruidStatInterceptor();
//        return interceptor;
//    }

    /**
     * Declare the JPA entity manager factory.
     */
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactory.setDataSource(_dataSource);
//        // Classpath scanning of @Component, @Service, etc annotated class
//        entityManagerFactory.setPackagesToScan(_env.getProperty("entitymanager.packagesToScan"));
//        // Vendor adapter
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
//        // Hibernate properties
//        Properties additionalProperties = new Properties();
//        additionalProperties.put("hibernate.dialect", _env.getProperty("hibernate.dialect"));
//        additionalProperties.put("hibernate.show_sql", _env.getProperty("hibernate.show_sql"));
//        additionalProperties.put("hibernate.hbm2ddl.auto", _env.getProperty("hibernate.hbm2ddl.auto"));
//        entityManagerFactory.setJpaProperties(additionalProperties);
//        return entityManagerFactory;
//    }


    /**
     * Declare the transaction manager.
     */
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(_entityManagerFactory.getObject());
//        return transactionManager;
//    }

    /**
     * PersistenceExceptionTranslationPostProcessor is a bean post processor
     * which adds an advisor to any bean annotated with Repository so that any
     * platform-specific exceptions are caught and then rethrown as one
     * Spring's unchecked data access exceptions (i.e. a subclass of
     * DataAccessException).
     */
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
}

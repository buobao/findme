package com.config;

import com.activeMQ.AdvancedNotifyMessageListener;
import com.activeMQ.AdvancedNotifyMessageProducer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * Created by dqf on 2015/8/24.
 */
@Configuration
public class JmsConfig {
    @Value("${spring.activemq.broker-url}")
    private String brokerURL;
    @Value("${jpush.appkey}")
    private String appkey;
    @Value("${jpush.mastersecret}")
    private String mastersecrect;

    /**
     * ActiveMQ连接工厂
     * @return
     */
//    @Bean(name = "factory")
//    public ActiveMQConnectionFactory advancedConnectionFactory(){
//        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
//        factory.setBrokerURL(brokerURL);
//        return factory;
//    }

    /**
     * Spring Caching 连接工厂
     * @return
     */
    @Bean
    public CachingConnectionFactory advancedCachingConnectionFactory(){
        ActiveMQConnectionFactory ffactory = new ActiveMQConnectionFactory();
        ffactory.setBrokerURL(brokerURL);
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setTargetConnectionFactory(ffactory);
        factory.setSessionCacheSize(10);
        return factory;
    }

    /**
     * Queue定义
     * @return
     */
    @Bean
    public ActiveMQQueue advancedNotifyQueue(){
        ActiveMQQueue queue = new ActiveMQQueue("q.advanced.notify");
        return  queue;
    }

    /**
     * Topic定义
     * @return
     */
    @Bean
    public ActiveMQTopic advancedNotifyTopic(){
        ActiveMQTopic topic = new ActiveMQTopic("t.advanced.notify");
        return topic;
    }

    /**
     * Spring JMS Template
     * @return
     */
    @Bean
    public JmsTemplate advancedJmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(advancedCachingConnectionFactory());
        //使 deliveryMode, priority, timeToLive设置生效
        template.setExplicitQosEnabled(true);
        //设置NON_PERSISTENT模式 默认为PERSISTENT
        template.setDeliveryPersistent(false);
        //设置优先级
        template.setPriority(9);
        return template;
    }

    /**
     * 使用Spring JmsTemplate的消息生产者
     * @return
     */
    @Bean
    public AdvancedNotifyMessageProducer advancedNotifyMessageProducer(){
        AdvancedNotifyMessageProducer producer = new AdvancedNotifyMessageProducer();
        producer.setJmsTemplate(advancedJmsTemplate());
        producer.setNotifyQueue(advancedNotifyQueue());
        producer.setNotifyTopic(advancedNotifyTopic());
        return producer;
    }

    /**
     * 异步接受消息处理类
     * @return
     */
    @Bean
    public AdvancedNotifyMessageListener advancedNotifyMessageListener(){
        AdvancedNotifyMessageListener listener = new AdvancedNotifyMessageListener();
        listener.setJpushAppKey(appkey);
        listener.setJpushMasterSecret(mastersecrect);
        return listener;
    }

    @Bean
    public DefaultMessageListenerContainer advancedQueueContainer(){
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(advancedCachingConnectionFactory());
        container.setDestination(advancedNotifyQueue());
        container.setMessageListener(advancedNotifyMessageListener());
        container.setConcurrentConsumers(5);
        container.setMaxConcurrentConsumers(10);
        container.setSessionAcknowledgeModeName("CLIENT_ACKNOWLEDGE");
        return container;
    }



}




























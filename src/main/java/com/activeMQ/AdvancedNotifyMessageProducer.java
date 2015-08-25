package com.activeMQ;

import com.bean.Email;
import com.bean.EnumManage;
import com.entity.sys.LocationEntity;
import com.entity.sys.PushEntity;
import com.entity.sys.PushLog;
import com.entity.sys.Sms;
import com.utils.DataUtil;
import com.utils.JsonMapper;
import com.utils.LogUtil;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.util.Date;

/**
 * Created by dqf on 2015/8/24.
 */
public class AdvancedNotifyMessageProducer {
    private static JsonMapper binder = JsonMapper.nonDefaultMapper();
    private JmsTemplate jmsTemplate;
    private Destination notifyQueue;
    private Destination notifyTopic;

    public AdvancedNotifyMessageProducer(){}

    //消息推送
    public void sendQueue(PushLog pushLog, String key){
        PushEntity pushEntity = new PushEntity(pushLog);
        String beanString = binder.toJson(pushEntity);
        String objectType = "pushEntity";
        String clazz = pushEntity.getClass().getName();
        this.sendMessage(key, beanString, clazz, "pushEntity", this.notifyQueue);
    }

    //推送短消息
    public void sendQueue(Sms sms, String key){
        String beanString = binder.toJson(sms);
        String objectType = "sms";
        String clazz = sms.getClass().getName();
        this.sendMessage(key, beanString, clazz, "sms", this.notifyQueue);
    }

    //推送邮件
    public void sendQueue(Email email, String key){
        String beanString = binder.toJson(email);
        String objectType = "email";
        String clazz = email.getClass().getName();
        this.sendMessage(key, beanString, clazz, "email", this.notifyQueue);
    }

    //位置信息推送
    public void sendQueue(LocationEntity locations, String key){
        if (locations.getCoordType().equals(EnumManage.CoordTypeEnum.bd09)){
            String beanString = binder.toJson(locations);
            String objectType = "locationEntity";
            String clazz = locations.getClass().getName();
            this.sendMessage(key, beanString, clazz, "locationEntity", this.notifyQueue);
        }
    }

    public void sendMessage(final String key, final String beanString, final String clazz, final String objectType, Destination destination){
        this.jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                long expiration = 0L;
                long timeStamp = System.currentTimeMillis();
                long timeToLive = 172800000L;
                if (timeToLive > 0L){
                    expiration = timeToLive + timeStamp;
                }

                MapMessage message = session.createMapMessage();
                message.setJMSExpiration(expiration);
                message.setString("key",key);
                message.setString("json",beanString);
                message.setString("clazz",clazz);
                message.setStringProperty("objectType", objectType);
                LogUtil.info("设置expiration过期日期: "+ DataUtil.DateToString(new Date(message.getJMSExpiration())));
                return message;
            }
        });
    }


    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setNotifyQueue(javax.jms.Destination notifyQueue) {
        this.notifyQueue = notifyQueue;
    }

    public void setNotifyTopic(javax.jms.Destination nodifyTopic) {
        this.notifyTopic = nodifyTopic;
    }
}





























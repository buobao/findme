package com.activeMQ;

import com.bean.Email;
import com.bean.EnumManage;
import com.entity.sys.PushEntity;
import com.entity.sys.Sms;
import com.service.common.SimpleMailService;
import com.service.sys.CompanyService;
import com.service.sys.LocationEntityService;
import com.service.sys.PushEntityService;
import com.service.sys.UsersService;
import com.utils.DataUtil;
import com.utils.JsonMapper;
import com.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Date;

/**
 * Created by dqf on 2015/8/24.
 */
public class AdvancedNotifyMessageListener implements MessageListener {
    @Resource
    private UsersService usersService;
    @Resource
    private SimpleMailService simpleMailService;
    @Resource
    private CompanyService companyService;
    @Resource
    private LocationEntityService locationEntityService;
    @Resource
    private PushEntityService pushEntityService;

    private String jpushAppKey;
    private String jpushMasterSecret;
    private static Logger logger = LoggerFactory.getLogger(AdvancedNotifyMessageListener.class);
    private static JsonMapper binder = JsonMapper.nonEmptyMapper();

    public AdvancedNotifyMessageListener(){}

    public String getJpushAppKey() {
        return this.jpushAppKey;
    }

    public void setJpushAppKey(String jpushAppKey) {
        this.jpushAppKey = jpushAppKey;
    }

    public String getJpushMasterSecret() {
        return this.jpushMasterSecret;
    }

    public void setJpushMasterSecret(String jpushMasterSecret) {
        this.jpushMasterSecret = jpushMasterSecret;
    }

    @Override
    public void onMessage(Message message) {
        MapMessage e = (MapMessage)message;
        try {
            LogUtil.info("获取expiration过期日期:"+ DataUtil.DateToString(new Date(message.getJMSExpiration())));
            String key = e.getString("key");
            String json = e.getString("json");
            String clazz = e. getString("clazz");
            String property = e.getStringProperty("objectType");
            if (key.equals(EnumManage.NotifyKeyEnum.sms.name())){
                Sms downloadRecord = (Sms)binder.fromJson(json, Sms.class);
            } else if(key.equals(EnumManage.NotifyKeyEnum.email.name())){
                Email dowloadRecord1 = (Email)binder.fromJson(json, Email.class);
                this.simpleMailService.send(dowloadRecord1);
            } else if (!key.equals(EnumManage.NotifyKeyEnum.apiRequest.name())){
                if (key.equals(EnumManage.NotifyKeyEnum.pushLog.name())){
                    //这里推送app消息
                    PushEntity downloadRedord2 = (PushEntity)binder.fromJson(json, PushEntity.class);
                    //downloadRecord2 = JPushUtil.send(downloadRecord2, this.jpushMasterSecret, this.jpushAppKey);
                    //this.pushEntityService.save(downloadRecord2);
                }  else if(key.equals(EnumManage.NotifyKeyEnum.locationEntity.name())) {
                    //位置信息推送，使用了百度地图,稍候添加
//                    LocationEntity downloadRecord3 = (LocationEntity)binder.fromJson(json, LocationEntity.class);
//                    Map map = BaiduMapUtil.getBd0911AddressMapFromWgs84(downloadRecord3.getLongitude().toString(), downloadRecord3.getLatitude().toString());
//                    downloadRecord3.setLongitude(new BigDecimal((String)map.get("x")));
//                    downloadRecord3.setLatitude(new BigDecimal((String)map.get("y")));
//                    downloadRecord3.setFullAddress((String)map.get("address"));
//                    downloadRecord3.setAddressJson((String)map.get("json"));
//                    this.locationEntityService.save(downloadRecord3);
                } else if(key.equals(EnumManage.NotifyKeyEnum.wxMpCustomMessage.name())) {
                    //微信推送
                    //WxMpCustomMessage downloadRecord4 = (WxMpCustomMessage)binder.fromJson(json, WxMpCustomMessage.class);
                    //this.wxMpService.customMessageSend(downloadRecord4);
                } else if(key.equals(EnumManage.NotifyKeyEnum.downloadRecord.name())) {
                    //DownloadRecord downloadRecord5 = (DownloadRecord)binder.fromJson(json, DownloadRecord.class);
                }
            }
        } catch (JMSException e1) {
            logger.error("处理消息时发生异常.", e1);
        }
    }
}
































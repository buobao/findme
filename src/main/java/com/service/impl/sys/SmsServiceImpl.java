package com.service.impl.sys;

import com.bean.Result;
import com.dao.base.BaseDao;
import com.dao.sys.SmsDao;
import com.entity.sys.Sms;
import com.service.common.ResultService;
import com.service.impl.base.BaseServiceImpl;
import com.service.sys.SmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Service实现类 - 用户账户信息
 * ============================================================================
 * 版权所有 2013 qshihua。
 * ----------------------------------------------------------------------------
 * 
 * @author qshihua
 * 
 * @version 0.1 2013-05-06
 */

@Service
public class SmsServiceImpl extends BaseServiceImpl<Sms, String> implements SmsService {

	@Resource
	private SmsDao smsDao;
    @Resource
    private ResultService resultService;
    //@Resource
    //private AdvancedNotifyMessageProducer notifyMessageProducer;

    @Override
    public BaseDao<Sms, String> getBaseDao() {
        return smsDao;
    }


    public Result send(String message,String account){
        //发送短信
//        Sms sms = new Sms(message, account, null);
//        save(sms);
//        notifyMessageProducer.sendQueue(sms, EnumManage.NotifyKeyEnum.sms.name());
//        return resultService.build(1,0, "短信已发送，请注意查收(如果未收到请查看是否被360等软件拦截)！",null);
        return null;
    }

}
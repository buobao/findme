package com.service.sys;

import com.bean.Result;
import com.entity.sys.Sms;
import com.service.base.BaseService;

/**
 * Created by dqf on 2015/8/18.
 */
public interface SmsService extends BaseService<Sms, String> {


    public Result send(String message, String account);
}

package com.service.impl.sys;

import com.dao.sys.BaseEntityDao;
import com.dao.sys.CustomMessageDao;
import com.entity.sys.CustomMessage;
import com.service.sys.CustomMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Service实现类 - 自定义消息
 * ============================================================================
 * 版权所有 2013 qshihua。
 * ----------------------------------------------------------------------------
 * 
 * @author qshihua
 * 
 * @version 0.1 2013-05-06
 */

@Service
public class CustomMessageServiceImpl extends BaseEntityServiceImpl<CustomMessage, String> implements CustomMessageService {

	@Resource
	private CustomMessageDao customMessageDao;

    @Override
    public BaseEntityDao<CustomMessage, String> getBaseEntityDao() {
        return customMessageDao;
    }

}
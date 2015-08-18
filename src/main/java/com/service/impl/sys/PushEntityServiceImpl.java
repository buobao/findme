package com.service.impl.sys;

import com.dao.base.BaseDao;
import com.dao.sys.PushEntityDao;
import com.entity.sys.PushEntity;
import com.service.impl.base.BaseServiceImpl;
import com.service.sys.PushEntityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Service实现类 - 推送
 * ============================================================================
 * 版权所有 2015 。
 *
 * @author fallenpanda
 *
 * @version 0.1 2015-01-21
 * ============================================================================
 */

@Service
public class PushEntityServiceImpl extends BaseServiceImpl<PushEntity, String> implements PushEntityService {

	@Resource
	private PushEntityDao pushEntityDao;

    @Override
    public BaseDao<PushEntity, String> getBaseDao() {
        return pushEntityDao;
    }




}
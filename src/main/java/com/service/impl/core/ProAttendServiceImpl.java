package com.service.impl.core;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.dao.core.ProAttendDao;
import com.dao.sys.BaseEntityDao;
import com.entity.core.ProAttend;
import com.entity.core.ProInfo;
import com.service.core.ProAttendService;
import com.service.impl.sys.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by amin on 2015/2/27.
 */
@Service
public class ProAttendServiceImpl extends BaseEntityServiceImpl<ProAttend,String> implements ProAttendService {

    @Resource
    ProAttendDao proAttendDao;


    @Override
    public BaseEntityDao<ProAttend, String> getBaseEntityDao() {
        return proAttendDao;
    }

    @Override
    public Pager findByPagerAndProInfo(Pager pager, ProInfo proInfo, BaseEnum.StateEnum[] states) {
        return proAttendDao.findByPagerAndProInfo(pager,proInfo,states);
    }

    @Override
    public List<ProAttend> getListByProInfo(ProInfo proInfo, BaseEnum.StateEnum[] states){
        List<ProAttend> list = proAttendDao.getListByProInfo(proInfo,states);
        return list;
    }
}

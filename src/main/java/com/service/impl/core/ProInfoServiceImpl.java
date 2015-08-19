package com.service.impl.core;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.bean.Result;
import com.dao.sys.BaseEntityDao;
import com.entity.core.Client;
import com.entity.core.ProInfo;
import com.entity.sys.Company;
import com.entity.sys.Users;
import com.service.core.ProInfoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dqf on 2015/8/19.
 */
@Service
public class ProInfoServiceImpl extends BaseLimitServiceImpl<ProInfo,String> implements ProInfoService {
    @Override
    public BaseEntityDao<ProInfo, String> getBaseEntityDao() {
        return null;
    }

    @Override
    public Map<String, Object> getListItemMap(ProInfo proInfo) {
        return null;
    }

    @Override
    public Map<String, Object> getDetailMap(ProInfo proInfo) {
        return null;
    }

    @Override
    public Pager findByPagerAndUsersRelated(Pager pager, Users users, Company company, Map<String, Object> params) {
        return null;
    }

    @Override
    public Pager findByPagerAndCompanyInBuild(Pager pager, Map<String, Object> rmap) {
        return null;
    }

    @Override
    public Pager findByPagerAndCompanyInFinish(Pager pager, Map<String, Object> rmap) {
        return null;
    }

    @Override
    public Pager findByPagerAndClient(Pager pager, Client client) {
        return null;
    }

    @Override
    public Result updateLocate(ProInfo proInfo, BigDecimal longitude, BigDecimal latitude) {
        return null;
    }

    @Override
    public List<ProInfo> getList(BaseEnum.StateEnum[] states) {
        return null;
    }

    @Override
    public Pager findByProInfoPager(Pager pager, Set<Users> usersSet, Company company, ProInfo proinfo, Map<String, Object> params) {
        return null;
    }

    @Override
    public Pager findChiefByPager(Pager pager, ProInfo proinfo, Map<String, Object> params) {
        return null;
    }
}

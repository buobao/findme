package com.service.core;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.bean.Result;
import com.entity.core.Client;
import com.entity.core.ProInfo;
import com.entity.sys.Company;
import com.entity.sys.Users;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dqf on 2015/8/19.
 */
public interface ProInfoService extends BaseLimitService<ProInfo,String> {
    /**
     * app使用
     * 转化为列表Map集合
     * @param proInfo
     * @return
     */
    public Map<String, Object> getListItemMap(ProInfo proInfo);

    /**
     * app使用
     * 转化为明细Map集合
     * @param proInfo
     * @return
     */
    public Map<String, Object> getDetailMap(ProInfo proInfo);
    /**
     * 和该用户相关的项目（项目总监或项目成员有该用户的项目）
     * @param pager
     * @param users
     * @param company
     * @param params
     * @return
     */
    public Pager findByPagerAndUsersRelated(Pager pager, Users users,Company company,Map<String, Object> params);

    /**
     * 根据公司查找所有实施中的项目
     * @param pager
     * @param rmap
     * @return
     */
    public Pager findByPagerAndCompanyInBuild(Pager pager, Map<String,Object> rmap);

    /**
     * 根据公司查找所有已完成的项目
     * @param pager
     * @param rmap
     * @return
     */
    public Pager findByPagerAndCompanyInFinish(Pager pager, Map<String,Object> rmap);

    /**
     * 根据客户查找所有的项目
     * @param pager
     * @param client
     * @return
     */
    public Pager findByPagerAndClient(Pager pager, Client client);

    /**
     * 更新地址
     * @param proInfo
     * @param longitude
     * @param latitude
     * @return
     */
    public Result updateLocate(ProInfo proInfo, BigDecimal longitude, BigDecimal latitude);

    /**
     * 获得所有项目信息
     * @param states
     * @return
     */
    public List<ProInfo> getList(BaseEnum.StateEnum[] states);
//    /**
//     * 根据权限查找
//     * @param pager
//     * @return
//     */
//    public Pager findByPagerAndLimit(Pager pager,Users users, Company company,Map<String,Object> rmap);

    /**
     * 获得项目组成员集合
     * @param pager
     * @param usersSet
     * @param company
     * @param proinfo
     * @param params
     * @return
     */
    public Pager findByProInfoPager(Pager pager, Set<Users> usersSet, Company company, ProInfo proinfo, Map<String,Object> params);

    /**
     * 获得项目总监
     * @param pager
     * @param proinfo
     * @param params
     * @return
     */
    public Pager findChiefByPager(Pager pager,ProInfo proinfo, Map<String,Object> params);

}

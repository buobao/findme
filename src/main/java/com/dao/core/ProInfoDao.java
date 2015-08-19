package com.dao.core;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.dao.sys.BaseEntityDao;
import com.entity.core.Client;
import com.entity.core.ProInfo;
import com.entity.sys.Company;
import com.entity.sys.Users;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by amin on 2015/2/27.
 */
public interface ProInfoDao extends BaseEntityDao<ProInfo,String> {
    /**
     * 根据公司查找所有实施中的项目
     * @param pager
     * @param rmap
     * @return
     */
    public Pager findByPagerAndCompanyInBuild(Pager pager, Map<String, Object> rmap);

    /**
     * 根据公司查找所有已完成的项目
     * @param pager
     * @param rmap
     * @return
     */
    public Pager findByPagerAndCompanyInFinish(Pager pager, Map<String, Object> rmap);

    /**
     * 根据客户查找所有项目
     * @param pager
     * @param client
     * @return
     */
    public Pager findByPagerAndClient(Pager pager, Client client);

    /**
     * 根据部门id和人查找项目
     * @param list
     * @param users
     * @return
     */
    public List<String> findByDepartmentOrUser(List<String> list, Users users);

    public List<ProInfo> getList(BaseEnum.StateEnum[] states);
    /**
     * 获得项目组成员集合
     * @param pager
     * @param usersSet
     * @param company
     * @param proinfo
     * @param params
     * @return
     */
    public Pager findByProInfoPager(Pager pager, Set<Users> usersSet, Company company, ProInfo proinfo, Map<String, Object> params) ;

    /**
     * 获得项目总监
     * @param pager
     * @param proinfo
     * @param params
     * @return
     */
    public Pager findChiefByPager(Pager pager, ProInfo proinfo, Map<String, Object> params);
}

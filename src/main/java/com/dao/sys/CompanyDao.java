package com.dao.sys;

import com.entity.sys.Company;
import com.entity.sys.Users;

import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
public interface CompanyDao extends BaseEntityDao<Company, String> {
    /**
     * 根据企业查找成员
     * @param company
     * @return
     */
    public List<Users> findUsersByCompany(Company company);
}

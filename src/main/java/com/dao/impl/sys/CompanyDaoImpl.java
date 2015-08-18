package com.dao.impl.sys;

import com.dao.sys.CompanyDao;
import com.entity.sys.Company;
import com.entity.sys.Users;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
@Repository
public class CompanyDaoImpl extends BaseEntityDaoImpl<Company, String> implements CompanyDao {
    @Override
    public List<Users> findUsersByCompany(Company company) {
        Assert.notNull(company, "company is required");
        String sql = " SELECT su.id from sys_users as su WHERE su.company_id=(SELECT sc.id from sys_company as sc WHERE sc.id=:i) AND su.state='enable'";
        return getSession().createQuery(sql).setParameter("i",company.getId()).list();
    }
}

























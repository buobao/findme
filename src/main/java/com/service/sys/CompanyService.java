package com.service.sys;

import com.entity.sys.Company;
import com.entity.sys.Users;

import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
public interface CompanyService extends BaseEntityService<Company, String> {
    public List<Users> findUsersByCompany(Company company);
}

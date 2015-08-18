package com.service.impl.sys;

import com.dao.sys.BaseEntityDao;
import com.dao.sys.CompanyDao;
import com.entity.sys.Company;
import com.entity.sys.Users;
import com.service.sys.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Service实现类 - 
 * ============================================================================
 * 版权所有 2013
 * ----------------------------------------------------------------------------
 * 
 * @author 
 * 
 * @version 0.1 2011-6-13
 */

@Service
public class CompanyServiceImpl extends BaseEntityServiceImpl<Company, String> implements CompanyService {
	
	@Resource
    CompanyDao companyDao;


	@Override
	public BaseEntityDao<Company, String> getBaseEntityDao() {
		return companyDao;
	}

    @Override
    public List<Users> findUsersByCompany(Company company) {
        return companyDao.findUsersByCompany(company);
    }
}
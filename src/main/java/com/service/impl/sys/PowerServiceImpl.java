package com.service.impl.sys;

import com.dao.sys.BaseEntityDao;
import com.dao.sys.PowerDao;
import com.entity.sys.Department;
import com.entity.sys.Duty;
import com.entity.sys.Post;
import com.entity.sys.Power;
import com.google.common.collect.Sets;
import com.service.sys.DutyService;
import com.service.sys.PowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;


/**
 * Service实现类 - 职权
 * ============================================================================
 * 版权所有 2013
 * ----------------------------------------------------------------------------
 * 
 * @author 
 * 
 * @version 0.1 2011-6-13
 */

@Service
public class PowerServiceImpl extends BaseEntityServiceImpl<Power, String> implements PowerService {
	
	@Resource
	PowerDao powerDao;
	@Resource
	public DutyService dutyService;


	@Override
	public BaseEntityDao<Power, String> getBaseEntityDao() {
		return powerDao;
	}

	@Override
	public List<Power> getByDepartId(String departId) {
		return powerDao.getByDepartId(departId);
	}

	@Override
	public Power getPowerByDepartAndPost(Department department, Post post) {
		return powerDao.getPowerByDepartAndPost(department, post);
	}

	@Override
	public Set<Duty> findParentByPower(Duty duty) {
		Power power = duty.getPower();
		Set<Duty> dutySet = Sets.newHashSet();
		if(power.getParent()==null){
			return dutySet;
		}
		List<Duty> dutyList = dutyService.findByPower(power.getParent());
		dutySet.addAll(dutyList);
		return dutySet;
	}
}
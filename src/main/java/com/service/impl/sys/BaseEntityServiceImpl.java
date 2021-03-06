package com.service.impl.sys;

import com.bean.BaseEnum;
import com.bean.Pager;
import com.bean.Result;
import com.dao.base.BaseDao;
import com.dao.sys.BaseEntityDao;
import com.entity.base.BaseEntity;
import com.entity.base.NameEntity;
import com.entity.sys.Company;
import com.entity.sys.Users;
import com.service.common.ResultService;
import com.service.impl.base.BaseServiceImpl;
import com.service.sys.BaseEntityService;
import com.utils.PinyinUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dqf on 2015/8/18.
 */
@Service
public abstract class BaseEntityServiceImpl<T extends BaseEntity, PK extends Serializable> extends BaseServiceImpl<T, PK> implements BaseEntityService<T, PK> {
    public abstract BaseEntityDao<T, PK> getBaseEntityDao();

    @Override
    public BaseDao<T, PK> getBaseDao() {
        return getBaseEntityDao();
    }

    @Resource
    protected ResultService resultService;

    @Override
    public PK save(T entity, BaseEnum.StateEnum state) {
        entity.setState(state);
        return save(entity);
    }

    @Override
    public PK save(T entity){
        entity.setState(BaseEnum.StateEnum.Enable);
        return super.save(entity);
    }

    @Override
    public PK saveAndEnable(T entity) {
        entity.setState(BaseEnum.StateEnum.Enable);
        return save(entity);
    }

    @Override
    public T updateAndEnable(T entity) {
        entity.setState(BaseEnum.StateEnum.Enable);
        if(StringUtils.isEmpty(entity.getId())){
            save(entity);
        }else{
            update(entity);
        }
        return entity;
    }

    @Override
    public T updateNameAndEnable(T entity) {
        if(entity instanceof NameEntity){
            if(StringUtils.isNoneEmpty(((NameEntity)entity).getName())){
                ((NameEntity)entity).setPinYin(PinyinUtil.getPingYin(((NameEntity) entity).getName()));
                ((NameEntity)entity).setPinYinHead(PinyinUtil.getPinYinHeadChar(((NameEntity) entity).getName()));
            }
        }
        return updateAndEnable(entity);
    }
    @Override
    public void enable(T entity){
        entity.setState(BaseEnum.StateEnum.Enable);
        if(StringUtils.isEmpty(entity.getId())){
            save(entity);
        }else{
            update(entity);
        }
    }
    @Override
    public void disable(T entity){
        entity.setState(BaseEnum.StateEnum.Disenable);
        if(StringUtils.isEmpty(entity.getId())){
            save(entity);
        }else{
            update(entity);
        }
    }

    @Override
    public Pager findByPagerBy(Pager pager, Users creater, BaseEnum.StateEnum... states) {
        Map< String, Object> params = new HashMap<String, Object>();
        if(creater!=null){
            params.put("creater", creater);
        }
        if(states!=null&&states.length>0){
            params.put("state", states);
        }
        return findByPager(pager, params);
    }

    @Override
    public List<T> getListBy(Users creater, BaseEnum.StateEnum... states) {
        Map< String, Object> params = new HashMap<String, Object>();
        if(creater!=null){
            params.put("creater", creater);
        }
        if(states!=null&&states.length>0){
            params.put("state", states);
        }
        return getList(params);
    }


    public Pager queryEnableByPager(Pager pager) {
        return getBaseEntityDao().findByPagerAndStates(pager, new BaseEnum.StateEnum[]{BaseEnum.StateEnum.Enable});
    }

    public Pager queryDeletedByPager(Pager pager) {
        return getBaseEntityDao().findByPagerAndStates(pager, new BaseEnum.StateEnum[]{BaseEnum.StateEnum.Delete});
    }

    public Pager queryValidByPager(Pager pager) {
        return getBaseEntityDao().queryValidByPager(pager);
    }

    public Pager findByPagerAndStates(Pager pager, BaseEnum.StateEnum[] states) {
        return getBaseEntityDao().findByPagerAndStates(pager, states);
    }

    @Override
    public Result checkReadAccess(T entity,Users users){
        return resultService.success();
        //resultService.build();
    }

    @Override
    public Result checkEditAccess(T entity,Users users){
        return resultService.success();
    }

    @Override
    public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria) {
        return getBaseEntityDao().findByPager(pager, detachedCriteria);
    }

    @Override
    public Pager findByPagerAndCompany(Pager pager, Users users,
                                       Company company, BaseEnum.StateEnum... states){
        return getBaseEntityDao().findByPagerAndCompany(pager,users,company, states);
    }

    @Override
    public Pager findByPagerAndCompany(Pager pager, Users users, Company company, Map<String, Object> params) {
        if(params==null){
            params = new HashMap<>();
        }
        params.put("state", new BaseEnum.StateEnum[]{BaseEnum.StateEnum.Enable});
        if(users!=null){
            params.put("creater", users);
        }
        if(company!=null){
            params.put("company", company);
        }
        return findByPager(pager, params, null, null);
    }


    @Override
    public Pager findByPagerAndCompany(Pager pager,String searchDate, Date beginDate, Date endDate,Users users,Company company,Map<String,Object> params){
        if(params==null){
            params = new HashMap<String,Object>();
        }
        params.put("state", new BaseEnum.StateEnum[]{BaseEnum.StateEnum.Enable});
        if(users!=null){
            params.put("creater", users);
        }
        if(company!=null){
            params.put("company", company);
        }
        return findByPager(pager,searchDate,beginDate,endDate,params);
    }
}

package com.service.module;

import com.bean.Pager;
import com.bean.Result;
import com.entity.module.EventBean;
import com.entity.module.Resume;
import com.service.base.MongoBaseService;

import java.util.Map;

/**
 * Created by dqf on 2015/8/19.
 */
public interface ResumeService extends MongoBaseService<Resume,String> {

    public Result createMongoEntity(Resume resume, StackTraceElement stack);
    public Result createMongoEntity(Resume resume);

    public Result updateMongoEntity(Resume resume);

    public Pager getTargetResume(Pager pager, String targetId);
    public Resume getLastTargetResume(String targetId);

    public Pager getCompanyResume(Pager pager, EventBean.EventEnum event, EventBean.TargetTypeEnum targetType, String companyId);

    public Map<String, Object> getListItemMap(Resume resume);
    public Map<String, Object> getDetailMap(Resume resume);
    public Map<String,Object> jqGridDetailMap(Pager pager, String usersId, String companyId, String targetId, Map<String, Object> params);
    public Map<String,Object> pagerToJqGridMap(Pager pager);

    public Pager getPager(Pager pager, String usersId, String companyId, String targetId, Map<String, Object> params);

}

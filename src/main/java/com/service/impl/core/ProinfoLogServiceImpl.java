package com.service.impl.core;

import com.alibaba.druid.util.StringUtils;
import com.bean.BaseEnum;
import com.dao.core.ProinfoLogDao;
import com.dao.sys.BaseEntityDao;
import com.entity.core.ProInfo;
import com.entity.core.ProInfoLog;
import com.entity.sys.Users;
import com.google.common.collect.Sets;
import com.service.core.ProInfoService;
import com.service.core.ProinfoLogService;
import com.service.impl.sys.BaseEntityServiceImpl;
import com.service.sys.UsersService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by amin on 2015/4/20.
 */
@Service
public class ProinfoLogServiceImpl extends BaseEntityServiceImpl<ProInfoLog, String> implements ProinfoLogService {
    @Resource
    ProinfoLogDao proinfoLogDao;
    @Resource
    ProInfoService proInfoService;
    @Resource
    UsersService usersService;

    @Override
    public BaseEntityDao<ProInfoLog, String> getBaseEntityDao() {
        return proinfoLogDao;
    }

    @Override
    public void updateProLog(ProInfo proInfo, Users users, Set<Users> group) {
        String chiefId=proInfo.getChief().getId();
        List<ProInfoLog> proInfoLogList= proinfoLogDao.getByProInfo(proInfo);
        Set<Users> oldSet = proInfo.getGroup();
        Set<String> s1 = Sets.newHashSet();
        Set<String> s2 = Sets.newHashSet();
        if(oldSet!=null && oldSet.size()>0){
            for (Users oldUser : oldSet) {
                s1.add(oldUser.getId());
            }
        }
       if(group!=null && group.size()>0){
           for (Users newUser : group) {
               s2.add(newUser.getId());
           }
       }

        ProInfoLog proInfoLog = new ProInfoLog();
        proInfoLog.setState(BaseEnum.StateEnum.Enable);
        proInfoLog.setProInfo(proInfo);
        proInfoLog.setCompany(usersService.getCompanyByUser());
        if(users!=null || group!=null){
            if (users!=null && StringUtils.equals(chiefId, users.getId())==false) {
                proInfoLog.setOldchief(proInfo.getChief());
                proInfoLog.setNewchief(users);
            }
            Set<Users> oSet= Sets.newHashSet();
            Set<Users> nSet= Sets.newHashSet();
            if(group!=null && CollectionUtils.isEqualCollection(s1, s2)==false){
                for(ProInfoLog log:proInfoLogList){
                    Set<Users> usersoSet=log.getOldgroup();
                    Set<Users> usersenSet=log.getNewgroup();
                    for(Users u:group){
                        //重新申明集合赋值，否则出现共享引用集合bug
                        //若oldGroup包含选到的重复的人，则移除,否则添加
                       if(usersoSet.size()>0){
                           if (usersoSet.contains(u)){
                               continue;
                           }
                           usersenSet.add(u);
                           oSet.addAll(usersenSet);
                           proInfoLog.setOldgroup(oSet);
                       }

                        //若newGroup包含选到的重复的人，则移除，否则添加
                        if(usersenSet.size()>0){
                            if(usersenSet.contains(u)){
                                continue;
                            }
                            usersenSet.add(u);
                            nSet.addAll(usersenSet);
                            proInfoLog.setNewgroup(nSet);
                        }else{
                            nSet.add(users);
                            proInfoLog.setNewgroup(nSet);
                        }
                    }
                }
            }
            if(StringUtils.equals(chiefId, users.getId())==false || CollectionUtils.isEqualCollection(s1, s2)==false){
                save(proInfoLog);
            }
        }
        proInfo.setChief(users);
        proInfo.setGroup(group);
        proInfoService.update(proInfo);

    }

    @Override
    public List<ProInfoLog> getByProInfo(ProInfo proInfo) {
        return proinfoLogDao.getByProInfo(proInfo);
    }

    @Override
    public void updateProInfoLog(ProInfo proInfo, ProInfoLog proInfoLog) {
        if (null !=proInfoLog.getOldchief() || null != proInfoLog.getNewchief()
                || null != proInfoLog.getNewgroup() || null != proInfoLog.getOldgroup()) {
            proInfoLog.setProInfo(proInfo);
            proInfoLog.setCompany(usersService.getCompanyByUser());
            save(proInfoLog);
        }
        proInfoService.update(proInfo);
    }
}

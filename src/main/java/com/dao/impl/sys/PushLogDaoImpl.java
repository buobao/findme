package com.dao.impl.sys;

import com.bean.BaseEnum;
import com.bean.EnumManage;
import com.bean.Pager;
import com.dao.sys.PushLogDao;
import com.entity.sys.PushLog;
import com.entity.sys.Users;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by dqf on 2015/8/18.
 */
@Repository
public class PushLogDaoImpl extends BaseEntityDaoImpl<PushLog, String> implements PushLogDao {

    @Override
    public Pager getPager(Pager pager, Users users, EnumManage.PushWayEnum pushWay, EnumManage.PushTypeEnum pushType,
                          int pushErrcode, boolean isSend, BaseEnum.StateEnum[] states) {
        if(pager != null){
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PushLog.class);
            if(users != null){
                detachedCriteria.createAlias("users", "users");
                detachedCriteria.add(Restrictions.or(Restrictions.eq("users", users), Restrictions.eq("users.id", users.getId())));
            }
            if(pushWay != null){
                detachedCriteria.add(Restrictions.eq("pushWay", pushWay));
            }
            if(pushType != null){
                detachedCriteria.add(Restrictions.eq("pushType", pushType));
            }
            if(pushErrcode != -1){
                detachedCriteria.add(Restrictions.eq("pushErrcode", pushErrcode));
            }

            detachedCriteria.add(Restrictions.eq("isSend", isSend));

            if(states!=null&&states.length>0){
                Criterion[] criterions = new Criterion[states.length];
                for(int i=0;i<states.length;i++){
                    criterions[i]= Restrictions.eq("state", states[i]);
                }
                detachedCriteria.add(Restrictions.or(criterions));
            }
            return findByPager(pager, detachedCriteria);
        }
        return null;
    }

    @Override
    public Pager getPager(Pager pager, Users users, EnumManage.PushWayEnum pushWay, EnumManage.PushTypeEnum pushType,
                          int pushErrcode, BaseEnum.StateEnum[] states) {
        if(pager != null){
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PushLog.class);
            if(users != null){
                detachedCriteria.createAlias("users", "users");
                detachedCriteria.add(Restrictions.or(Restrictions.eq("users", users), Restrictions.eq("users.id", users.getId())));
            }
            if(pushWay != null){
                detachedCriteria.add(Restrictions.eq("pushWay", pushWay));
            }
            if(pushType != null){
                detachedCriteria.add(Restrictions.eq("pushType", pushType));
            }
            if(pushErrcode != -1){
                detachedCriteria.add(Restrictions.eq("pushErrcode", pushErrcode));
            }
            if(states!=null&&states.length>0){
                Criterion[] criterions = new Criterion[states.length];
                for(int i=0;i<states.length;i++){
                    criterions[i]= Restrictions.eq("state", states[i]);
                }
                detachedCriteria.add(Restrictions.or(criterions));
            }
            return findByPager(pager, detachedCriteria);
        }
        return null;
    }

    @Override
    public Pager getPager(Pager pager, Users users, EnumManage.PushWayEnum pushWay, EnumManage.PushTypeEnum pushType,
                          int pushErrcode,Date date, BaseEnum.StateEnum[] states) {
        if(pager != null){
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PushLog.class);
            if(users != null){
                detachedCriteria.createAlias("users", "users");
                detachedCriteria.add(Restrictions.or(Restrictions.eq("users", users), Restrictions.eq("users.id", users.getId())));
            }
            if(pushWay != null){
                detachedCriteria.add(Restrictions.eq("pushWay", pushWay));
            }
            if(pushType != null){
                detachedCriteria.add(Restrictions.eq("pushType", pushType));
            }
            if(pushErrcode != -1){
                detachedCriteria.add(Restrictions.eq("pushErrcode", pushErrcode));
            }
            if(date != null){
                detachedCriteria.add(Restrictions.ge("createDate", date));
            }
            if(states!=null&&states.length>0){
                Criterion[] criterions = new Criterion[states.length];
                for(int i=0;i<states.length;i++){
                    criterions[i]= Restrictions.eq("state", states[i]);
                }
                detachedCriteria.add(Restrictions.or(criterions));
            }
            return findByPager(pager, detachedCriteria);
        }
        return null;
    }
}

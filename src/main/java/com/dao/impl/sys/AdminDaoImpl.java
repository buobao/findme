package com.dao.impl.sys;

import com.bean.BaseEnum;
import com.dao.sys.AdminDao;
import com.entity.sys.Admin;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
@Repository
public class AdminDaoImpl extends BaseEntityDaoImpl<Admin, String> implements AdminDao {
    @Override
    public Admin getByMobile(String username) {
        String hql = "from Admin where usermobile = ? and state = ?";
        return (Admin) getSession().createQuery(hql).setParameter(0, username).setParameter(1, BaseEnum.StateEnum.Enable).uniqueResult();
    }

    @Override
    public Admin getAdminByOpenId(String openId) {
        Session session = sessionFactory.openSession();
        Admin admin = null;
        try{
            String hql = "from Admin where openId = ? and state = ? ";
            admin = (Admin)session.createQuery(hql).setParameter(0,openId).setParameter(1,BaseEnum.StateEnum.Enable).uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null){
                session.close();
            }
        }

        return admin;
    }

    @Override
    public Admin getAdminByUsermobile(String usermobile) {
        String hql = "from Admin where lower(usermobile) = lower(:usermobile) ";
        return (Admin)getSession().createQuery(hql).setParameter("usermobile",usermobile).uniqueResult();
    }

    @Override
    public List<Admin> getList(BaseEnum.StateEnum[] states) {
        String hql = "from "+Admin.class.getName() + " model where model.state in :state ";
        List<Admin> list = getSession().createQuery(hql).setParameter("state",BaseEnum.StateEnum.Enable).list();
        return list;
    }

    @Override
    public Admin getAdminByDeviceId(String deviceId) {
        String hql = "from Admin where deviceId = ? and state = ?";
        return (Admin) getSession().createQuery(hql).setParameter(0, deviceId).setParameter(1, BaseEnum.StateEnum.Enable).uniqueResult();
    }
}

































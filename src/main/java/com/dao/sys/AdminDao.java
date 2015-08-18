package com.dao.sys;

import com.bean.BaseEnum;
import com.entity.sys.Admin;

import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
public interface AdminDao extends BaseEntityDao<Admin, String> {
    /**
     * 根据手机号码查找帐号
     * @param username
     * @return
     */
    public Admin getByMobile(String username);

    /**
     * 根据openId获取管理员对象，若管理员不存在，则返回null（区分大小写）
     * @param openId
     *
     * @return
     */
    public Admin getAdminByOpenId(String openId);

    public Admin getAdminByUsermobile(String usermobile);

    public List<Admin> getList(BaseEnum.StateEnum[] states);

    /**
     * 根据deviceId获取管理员对象，若管理员不存在，则返回null（区分大小写）
     * @param deviceId
     * @return
     */
    public Admin getAdminByDeviceId(String deviceId);
}

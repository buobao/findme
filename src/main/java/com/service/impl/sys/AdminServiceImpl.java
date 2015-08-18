package com.service.impl.sys;

import com.bean.BaseEnum;
import com.bean.EnumManage;
import com.bean.Result;
import com.dao.sys.AdminDao;
import com.dao.sys.BaseEntityDao;
import com.entity.sys.Admin;
import com.service.sys.AdminService;
import com.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Service实现类 - 账户
 * ============================================================================
 * 版权所有 2013
 * ----------------------------------------------------------------------------
 * 
 * @author 
 * 
 * @version 0.1 2011-6-13
 */

@Service
public class AdminServiceImpl extends BaseEntityServiceImpl<Admin, String> implements AdminService {
	
	@Resource
    AdminDao adminDao;


	@Override
	public BaseEntityDao<Admin, String> getBaseEntityDao() {
		return adminDao;
	}

    @Override
    public boolean isDeviceIdRepeat(Admin admin, String deviceId) {
        Admin adminObj = adminDao.getAdminByDeviceId(deviceId);
        if (adminObj != null && !(adminObj.getId().equals(admin.getId()))){
            return true;
        }
        return false;
    }

    @Override
    public Result registe(String name,String password,EnumManage.AccountType accountType){
        BaseEnum.AccountEnum accountEnum = BaseEnum.AccountEnum.MOBILE;

        //密码用name+salt进行加密
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        String encodedPassword = encodedPassword(name,password,salt);
        Admin admin = null;
        if(adminDao.get("username",name)==null){
            admin = new Admin(name,encodedPassword,salt,true,accountEnum,accountType);
        }else {
            admin = adminDao.get("username",name);
            admin.setPassword(encodedPassword);
            admin.setSalt(salt);
            admin.setIsAccountEnabled(true);
            admin.setAccountEnum(accountEnum);
            admin.setAccountType(accountType);
        }

        admin.setUsermobile(name);

        updateAndEnable(admin);
        return resultService.successWithId(admin.getId());
    }

    @Override
    public Admin getByMobile(String username) {
        return adminDao.getByMobile(username);
    }

    @Override
    public Admin getAdminByOpenId(String openId) {
        return adminDao.getAdminByOpenId(openId);
    }

    @Override
    public Result updateOpenId(Admin admin, String openId) {
        Admin tmpAdmin = getAdminByOpenId(openId);
//        if(tmpAdmin == null){
//            admin.setOpenId(openId);
//            update(admin);
//            return resultService.success();
//        }else{
//            if(StringUtils.equals(tmpAdmin.getId(),admin.getId())){
//                return resultService.success();
//            }else{
//                return resultService.error();
//            }
//        }

        admin.setOpenId(openId);
        update(admin);
        return resultService.success();
    }

    public boolean validPassword(Admin admin,String password){
        String name = admin.getUsername();
        if(admin.getAccountEnum().name().equals(BaseEnum.AccountEnum.MAIL.name())){
            name = admin.getUsername();
        }else if(admin.getAccountEnum().name().equals(BaseEnum.AccountEnum.MOBILE.name())){
            name = admin.getUsermobile();
        }else{
            return false;
        }

        //密码用name+salt进行加密
        String salt = admin.getSalt();
        String encodedPassword = encodedPassword(name,password,salt);
        return StringUtils.equals(admin.getPassword(), encodedPassword);
    }
    /**
     * 获取加密密码
     * */
    @Override
    public String encodedPassword(String name,String password,String salt){
        int hashIterations = 2;
        SimpleHash hash = new SimpleHash("md5", password, name + salt, hashIterations);
        return hash.toHex();
    }

    /**
     * 通用用户名调用方法，无效返回null
     * */
    public Admin getAdminByAccount(String name){
        if(CommonUtil.isMobile(name)){
            return getAdminByUsermobile(name);
        }
        return null;
    }

    @Override
    public List<Admin> getList(BaseEnum.StateEnum[] states) {
        List<Admin> list = adminDao.getList(states);
        return  list;
    }

    public Admin getAdminByUsermobile(String usermobile){
        return adminDao.getAdminByUsermobile(usermobile);
    }

}
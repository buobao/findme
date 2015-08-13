package com.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.io.Serializable;

/**
 * Created by dqf on 2015/8/13.
 */
public class ShiroRealmImpl extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ShiroUser shiroUser = (ShiroUser) principalCollection.fromRealm(getName()).iterator().next();
        String userName = shiroUser.getName();
        if (StringUtils.equals("admin",userName)){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //这里确定页面中<shiro:hasPermission>标签的name的值
            info.addRole("admin");
            //这个就是页面中<shiro:hasPermission>标签的name的值
            info.addStringPermission("user:edit");
            return info;
        } else if (StringUtils.equals("normal",userName)){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRole("normal");
            info.addStringPermission("user:view");
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String encodedPassword = String.valueOf(usernamePasswordToken.getPassword());

        System.out.println("====================Authentication  begin ==========================");
        System.out.println("username: " + username);
        System.out.println("password: " + encodedPassword);
        System.out.println("principal: " + usernamePasswordToken.getPrincipal());
        System.out.println("======================Authentication  end ========================");

        return new SimpleAuthenticationInfo(new ShiroUser("UserId","password","info")
        ,String.valueOf(usernamePasswordToken.getPassword()), ByteSource.Util.bytes("userId"),getName());
    }



    public static class ShiroUser implements Serializable{
        private static final long serialVersionUID = -1373760761780849981L;
        public String id;
        public String info;
        public String name;

        public ShiroUser(String id, String name, String info){
            this.id = id;
            this.info = info;
            this.name = name;
        }

        public ShiroUser(String name, String info){
            this.info = info;
            this.name = name;
        }

        public String getName(){return name;}

        @Override
        public String toString(){return info;}

        public String getDisplayName(){return name;}
    }
}

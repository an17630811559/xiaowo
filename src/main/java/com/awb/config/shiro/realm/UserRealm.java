package com.awb.config.shiro.realm;

import com.awb.constants.UserStatus;
import com.awb.entity.User;
import com.awb.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author AAA QY102 awb
 * @description
 * @date create in 14:27 2020/4/17
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null)
        {
            password = new String(upToken.getPassword());
        }

        User user = userService.queryByUser(new User().setUsername(username).setPassword(password));

        if (null==user){
            throw new UnknownAccountException(UserStatus.NO_USER);
        }
        SimpleAuthenticationInfo sai=new SimpleAuthenticationInfo(user,password,getName());

        return sai;
    }
}

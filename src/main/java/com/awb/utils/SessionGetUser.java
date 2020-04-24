package com.awb.utils;

import com.awb.entity.User;
import org.apache.shiro.SecurityUtils;

/**
 * @author AAA QY102 awb
 * @description 获取session中的user对象
 * @date create in 10:27 2020/4/18
 */
public class SessionGetUser {
    public static User getUser(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        return user;
    }
}

package com.awb.controller;

import com.awb.base.ResponseResult;
import com.awb.config.annotation.OptionalLog;
import com.awb.constants.UserStatus;
import com.awb.entity.User;
import com.awb.service.UserService;
import com.awb.utils.SessionGetUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author awb
 * @since 2020-04-04 11:27:23
 */
@Controller
@ResponseResult
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    @ResponseBody
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }


    @OptionalLog("用户登录")
    @PostMapping("login")
    @ResponseBody
    public Map<String,Object> login(String username,String password){
        Map<String,Object> map=new HashMap<String,Object>();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            map.put("msg","欢迎用户: '"+username+"' 回家!");
            map.put("code","200");
        }catch (Exception e){
            map.put("msg","用户账户或密码错误");
            map.put("code","500");
        }
        return map;
    }

    /**
     * 搜索用户
     */
    @OptionalLog("搜索用户")
    @PostMapping("search")
    @ResponseBody
    public List search(String key){
        List result = userService.search(key);
        if (null==result ||0== result.size()){
            result.add("我们这里没有这个人呢!");
        }
        return result;
    }

    /**
     * 搜索用户
     */
    @OptionalLog("获取当前登录用户")
    @GetMapping("getUser")
    @ResponseBody
    public User getUser(){
        User user = SessionGetUser.getUser();
        return user;
    }

    @PostMapping("checkPasd")
    @ResponseBody
    public Map<String,Boolean> checkPasd(String password){
        Map<String,Boolean> map=new HashMap<String, Boolean>();

        User user = SessionGetUser.getUser();
        map.put(UserStatus.CHECKPASD_RESULT,userService.checkPassword(user.setPassword(password)));
        return map;
    }

    @OptionalLog("修改密码")
    @PostMapping("editPasd")
    @ResponseBody
    public void editPasd(String password){
        User user = SessionGetUser.getUser();
        user.setPassword(password);
        userService.update(user);
    }

}
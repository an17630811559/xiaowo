package com.awb.controller;

import com.awb.base.ResponseResult;
import com.awb.config.annotation.OptionalLog;
import com.awb.entity.Msg;
import com.awb.entity.User;
import com.awb.entity.vo.MsgAndUser;
import com.awb.service.MsgService;
import com.awb.utils.SessionGetUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Msg)表控制层
 *
 * @author awb
 * @since 2020-04-04 14:58:34
 */
@Controller
@ResponseResult
@RequestMapping("msg")
public class MsgController {
    /**
     * 服务对象
     */
    @Resource
    private MsgService msgService;

    @Value("${msg.page-size}")
    private Integer pagesize;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @OptionalLog("查询单条")
    @GetMapping("selectOne")
    @ResponseBody
    public Msg selectOne(Integer id) {
        return this.msgService.queryById(id);
    }



    @OptionalLog("查询留言板")
    @RequestMapping("selectAll")
    public @ResponseBody Map<String,Object> selectAll(Integer currentPage,Integer pageSize,Integer status) {
        Map<String,Object> map=new HashMap<String,Object>();
        if (null==pageSize){
            pageSize=pagesize;
        }
        User user = SessionGetUser.getUser();
        PageHelper.startPage(currentPage, pageSize);
        map.put("user", user);
        List<MsgAndUser> msgs=new ArrayList<MsgAndUser>();
        //是查询单人
        if (status==1){
            msgs = msgService.queryAllByUid(user.getId());
        }else {
            msgs= msgService.queryAllByLimit();
        }
        PageInfo<MsgAndUser> pageInfo = new PageInfo<MsgAndUser>(msgs);
        map.put("msg",pageInfo);
        return map;
    }

    @OptionalLog("提交留言")
    @PostMapping("uploadMsg")
    @ResponseBody
    public void uploadMsg(Msg msg){
        msgService.insert(msg.setUid(SessionGetUser.getUser().getId()));
    }

    @OptionalLog("修改留言")
    @PostMapping("editMsg")
    public @ResponseBody void editMsg(Msg msg,Integer sta){
        //sta为识别是否是编辑或者是删除的变量  0:删除  1:编辑
        if (sta==0){
              msgService.deleteById(msg.getId());
        }else{
              msgService.update(msg);
        }

    }



}
package com.awb.controller;

import com.awb.config.annotation.OptionalLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author AAA QY102 awb
 * @description
 * @date create in 11:56 2020/4/4
 */
@Controller
public class IndexController {

    /**
     * 跳转主页
     * @return
     */
    @RequestMapping("toIndex")
    public String toIndex1() {
        return "index";
    }

    @OptionalLog("跳转主页")
    @RequestMapping("/")
    public String toIndex2() {
        return "index";
    }

    @OptionalLog("跳转留言板")
    @GetMapping("toMessage")
    public String toMessage(){
        return "message";
    }

    @OptionalLog("跳转最后登录查询")
    @GetMapping("toSearch")
    public String toSearch(){
        return "search";
    }

    @OptionalLog("跳转天气页面")
    @GetMapping("toForecast")
    public String toForecast(){
        return "forecast";
    }


    @OptionalLog("跳转登录")
    @GetMapping("toLogin")
    public String toLogin(){
        return "login";
    }
}

package com.awb.controller;

import com.awb.base.ResponseResult;
import com.awb.config.annotation.OptionalLog;
import com.awb.entity.vo.ForecastAndCasts2;
import com.awb.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author AAA QY102 awb
 * @description
 * @date create in 11:19 2020/4/9
 */
@Controller
@ResponseResult
@RequestMapping("forecast")
public class ForecastController {

    @Autowired
    private ForecastService forecastService;

    @OptionalLog("获取当前天气")
    @GetMapping("getData")
    @ResponseBody
    public List<ForecastAndCasts2> getData(String city){
        return forecastService.getForecast(city);
    }

}

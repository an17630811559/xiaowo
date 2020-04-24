package com.awb.service;


import com.awb.entity.Forecast;
import com.awb.entity.vo.ForecastAndCasts;
import com.awb.entity.vo.ForecastAndCasts2;

import java.util.List;

/**
 * @author AAA QY102 awb
 * @description
 * @date create in 11:28 2020/4/9
 */
public interface ForecastService {

    /**
     * 查询当前天气是否存在
     * @param forecast
     * @return
     */
    List<Forecast> getForecast(ForecastAndCasts forecast);

    /**
     * 插入天气
     * @param forecast
     * @return
     */
    Forecast insert(ForecastAndCasts forecast);


    List<ForecastAndCasts2> getForecast(String city);
}

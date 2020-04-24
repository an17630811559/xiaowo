package com.awb.service.impl;

import com.awb.entity.Forecast;
import com.awb.entity.vo.ForecastAndCasts;
import com.awb.entity.vo.ForecastAndCasts2;
import com.awb.mapper.ForecastMapper;
import com.awb.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AAA QY102 awb
 * @description
 * @date create in 11:29 2020/4/9
 */
@Service("forecastService")
public class ForecastServiceImpl implements ForecastService {

    @Autowired
    private ForecastMapper forecastMapper;


    @Override
    public List<Forecast> getForecast(ForecastAndCasts forecast) {
        return forecastMapper.queryAll(forecast);
    }

    @Override
    public Forecast insert(ForecastAndCasts forecast) {
        forecastMapper.insert(forecast);
        return forecast;
    }

    @Override
    public List<ForecastAndCasts2> getForecast(String city) {
        List<ForecastAndCasts2> result = forecastMapper.getdata(city);
        return result;
    }
}

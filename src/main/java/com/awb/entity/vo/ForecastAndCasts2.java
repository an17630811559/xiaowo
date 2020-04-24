package com.awb.entity.vo;

import com.awb.entity.Forecast;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author AAA QY102 awb
 * @description
 * @date create in 16:03 2020/4/9
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ForecastAndCasts2 extends Forecast {
    /**
     * 日期
     */    private String date;
    /**
     * 白天天气
     */    private String dayweather;
    /**
     * 晚上天气
     */    private String nightweather;
    /**
     * 白天温度
     */    private String daytemp;
    /**
     * 晚上温度
     */    private String nighttemp;
    /**
     * 白天风向
     */    private String daywind;
    /**
     * 晚上风向
     */    private String nightwind;
    /**
     * 白天风力
     */    private String daypower;
    /**
     * 晚上风力
     */    private String nightpower;

    /**
     * fid
     */    private  Integer fid;
}

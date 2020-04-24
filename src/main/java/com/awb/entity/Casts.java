package com.awb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (Casts)实体类
 *
 * @author awb
 * @since 2020-04-09 11:01:18
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Casts implements Serializable {
    private static final long serialVersionUID = 804376258413655239L;
        private Integer id;
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
package com.awb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (Forecast)实体类
 *
 * @author awb
 * @since 2020-04-09 17:12:26
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Forecast implements Serializable {
    private static final long serialVersionUID = -64387377946678772L;
        private Integer id;
    /**
    * 城市名称
    */    private String city;
    /**
    * 城市编码
    */    private String adcode;
    /**
    * 省份
    */    private String province;
    /**
    * 时间
    */    private String reporttime;

}
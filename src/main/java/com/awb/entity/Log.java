package com.awb.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author AAA QY102 awb
 * @description
 * @date create in 16:23 2020/4/6
 */
@lombok.Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    private Long id;
    private String user;
    private Long exeuTime;
    private String createDate;
    private String remark;
    private String className;
    private String methodName;
    private String params;
    private String ipAdress;

}

package com.awb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description
 *    启动类
 * @author awb
 * @since 2020-04-04 11:27:23
 */
@SpringBootApplication
//开启定时任务
@EnableScheduling
@MapperScan("com.awb.mapper")
public class ApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class,args);
    }
}
package com.awb.config.scheduling;

import com.alibaba.fastjson.JSON;
import com.awb.entity.Casts;
import com.awb.entity.Forecast;
import com.awb.entity.vo.ForecastAndCasts;
import com.awb.service.CastsService;
import com.awb.service.ForecastService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author AAA QY102 awb
 * @description 定时任务类
 * @date create in 11:27 2020/4/9
 */
@Component
public class ScheduledTask {

    @Autowired
    private ForecastService forecastService;

    @Autowired
    private CastsService castsService;

    @Value("${gaode.forecast}")
    private String url;

    @Value("${gaode.adcode}")
    private String adcode;
    /**
     * @author awb
     * @description
     *      更新天气
     * @date create in 17:04 2019/12/21
     * @param
     * @return
     * 0/10 * * * * *
     * 0 0 9,12,24 * * ?
     */
    @Scheduled(cron ="0 0 9,12,23 * * ?")
    public void reloadForecast(){

        HttpClient httpClient = new HttpClient();
        //自动管理Cookie
        httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        try{
            Forecast(httpClient);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void Forecast(HttpClient httpClient)throws Exception{

        String[] code = adcode.split(",");
        for(String c:code){
            GetMethod getMethod=new GetMethod(url+c);
            httpClient.executeMethod(getMethod);
            String text = getMethod.getResponseBodyAsString();
            Map map = JSON.parseObject(text,Map.class);
            if (map.get("status").equals("1")){
                if (map.get("count").equals("1")){
                    List<ForecastAndCasts> ForecastAndCasts = JSON.parseArray(map.get("forecasts").toString(), com.awb.entity.vo.ForecastAndCasts.class);
                    //先插入f表 判断这次更新时间是否与上次更新时间一致 ,一致的话不更新
                    //先查询 条件 adcode date 是否存在 不存在就添加 存在的话不变
                    com.awb.entity.vo.ForecastAndCasts forecastAndCasts=ForecastAndCasts.get(0);
                    //拿到实体类
                    List<Forecast> forecast = forecastService.getForecast(forecastAndCasts);
                    //如果没查到
                    if (null==forecast || 0==forecast.size()){
                        //插入数据
                        Forecast Forecast = forecastService.insert(forecastAndCasts);
                        //如果插入成功
                        if (null!=Forecast){
                            Integer id = Forecast.getId();
                            List<Casts> casts = forecastAndCasts.getCasts();
                            for(Casts ca:casts){
                                ca.setFid(id);
                                 castsService.insert(ca);
                            }
                        }
                    }
                }
            }

        }


    }
}

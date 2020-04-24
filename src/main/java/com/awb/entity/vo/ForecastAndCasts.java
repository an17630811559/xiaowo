package com.awb.entity.vo;

import com.awb.entity.Casts;
import com.awb.entity.Forecast;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author AAA QY102 awb
 * @description
 * @date create in 16:03 2020/4/9
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ForecastAndCasts extends Forecast {
    private List<Casts> casts;
}

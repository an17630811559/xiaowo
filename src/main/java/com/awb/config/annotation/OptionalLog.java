package com.awb.config.annotation;

import java.lang.annotation.*;

/**
 * @author awb
 * @description
 * @date create in 16:53 2020/4/6
 * @param
 * @return
*/
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptionalLog {
    String value() default "";
}

package com.awb.config.aspect;

import com.alibaba.fastjson.JSON;
import com.awb.config.annotation.OptionalLog;
import com.awb.entity.Log;
import com.awb.entity.User;
import com.awb.service.impl.LogServiceImpl;
import com.awb.utils.IpUtil;
import com.awb.utils.SessionGetUser;
import org.apache.catalina.session.StandardSessionFacade;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author AAA QY102 awb
 * @description
 * @date create in 16:18 2020/4/6
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    public LogServiceImpl sysLogService;

    /**
     * 这里我们使用注解的形式 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method 切点表达式:
     * execution(...)
     */
    @Pointcut("@annotation(com.awb.config.annotation.OptionalLog)")
    public void logPointCut() {

    }

    /**
     * 环绕通知 @Around ， 当然也可以使用 @Before (前置通知) @After (后置通知)
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @After("logPointCut()")
    public void around(JoinPoint point) {
        try {
            saveLog(point);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存日志
     *
     * @param joinPoint
     */
    private void saveLog(JoinPoint joinPoint) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log sysLogBO = new Log();

        sysLogBO.setExeuTime(0L);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sysLogBO.setCreateDate(dateFormat.format(new Date()));

        OptionalLog sysLog = method.getAnnotation(OptionalLog.class);
        //获取session中的用户
        User user = SessionGetUser.getUser();
        if (null != user) {
            String userId = JSON.toJSON(user.getId()).toString();
            sysLogBO.setUser(userId);
        } else {
            sysLogBO.setUser("游客");
        }
        if (sysLog != null) {
            // 注解上的描述
            sysLogBO.setRemark(sysLog.value());
        }
        // 请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLogBO.setClassName(className);
        sysLogBO.setMethodName(methodName);
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            List<String> list = new ArrayList<String>();
            for (Object o : args) {
                StandardSessionFacade standardSessionFacade = (StandardSessionFacade) o;
                /*StandardSession standardSession= standardSessionFacade;*/
                list.add(JSON.toJSONString(standardSessionFacade.getAttribute("")));
            }
            sysLogBO.setParams(list.toString());
        } catch (Exception e) {
            sysLogBO.setParams("参数名过长");
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        sysLogBO.setIpAdress(IpUtil.getIpAddress(request));

        sysLogService.insert(sysLogBO);
    }

}

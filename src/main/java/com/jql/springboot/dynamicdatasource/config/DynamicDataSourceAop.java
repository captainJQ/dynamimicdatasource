package com.jql.springboot.dynamicdatasource.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
public class DynamicDataSourceAop {

    @Pointcut("execution(* com.jql.springboot.dynamicdatasource.controller.*.*(..))")
    public void pointCut() {
    }

    /**
     * 执行方法前更换数据源
     */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String area = (String) request.getHeader("area");
        System.out.println("AREA:" + area);
        if (AreaAndKey.BEIJING.getKey().equals(area)) {
            DynamicDataSourceContextHolder.set(AreaAndKey.BEIJING);
        } else if (AreaAndKey.SHENZHEN.getKey().equals(area)) {
            DynamicDataSourceContextHolder.set(AreaAndKey.SHENZHEN);
        }
    }

    /**
     * 执行方法后清除数据源设置
     */
    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        // 清楚数据源
        DynamicDataSourceContextHolder.clear();
    }

}

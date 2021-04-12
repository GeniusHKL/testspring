package com.yc.aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
@Order(value = 1)
public class LogAspect3 {
    @Around("execution(* com.yc.biz.StudentBizImpl.find*(..))")
    public Object computer2(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("=============computer2进到增强了");
        long start=System.currentTimeMillis();
        Object retVal=pjp.proceed();
        long end=System.currentTimeMillis();
        System.out.println("======computer2要退出了");
        System.out.println("=============这个方法2运行的时长为："+(end-start));
        return retVal;
    }
}

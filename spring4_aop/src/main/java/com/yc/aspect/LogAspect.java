package com.yc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
@Order(value = 100)
public class LogAspect {
    //切入点的声明
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.add*(..))")  //切入点表达式
    private void add(){
    }

    @Pointcut("execution(* com.yc.biz.StudentBizImpl.update*(..))")
    private void update(){

    }
    @Pointcut("add() || update()")
    private void addAndUpdate(){

    }
    //增强的声明
//    @Before("com.yc.aspect.LogAspect.addAndUpdate()")
//    public void log(){
//        System.out.println("=======前置增强的日志============");
//        Date d=new Date();
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dstr=sdf.format(d);
//        System.out.println("执行时间为："+dstr);
//        System.out.println("=======前置增强的日志结束=======");
//    }
    @Around("execution(* com.yc.biz.StudentBizImpl.find*(..))")
    public Object computer(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("=============computer进到增强了");
        long start=System.currentTimeMillis();
        Object retVal=pjp.proceed();
        long end=System.currentTimeMillis();
        System.out.println("======computer要退出了");
        System.out.println("=============这个方法运行的时长为："+(end-start));
        return retVal;
    }
//    @After("com.yc.aspect.LogAspect.addAndUpdate()")
//    public void bye(JoinPoint jp){
//        System.out.println("========bye========");
//        Object target=jp.getTarget();
//        System.out.println("目标类为："+target);
//        System.out.println("方法："+jp.getSignature());
//        Object[] objs=jp.getArgs();
//        if(objs!=null){
//            for(Object o:objs){
//                System.out.println("参数："+o);
//            }
//        }
//        System.out.println("========bye========");
//    }

}

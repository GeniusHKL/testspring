package com.yc;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogAspectCglib implements MethodInterceptor {

    private Object target;

    public LogAspectCglib(Object target){
        this.target=target;

    }
    public Object createProxy(){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理类的对象:"+o.getClass());
        System.out.println("目标类的方法:"+method);
        System.out.println("方法中的参数:"+args);
        System.out.println("要代理的方法:"+methodProxy);
        if(method.getName().equals("add")){  //转换成切入点表达式的解析(写死了)   -》AspectJ的切入点表达式
            //前置增强
            log();
        }
        Object returnValue=method.invoke(this.target,args);
        return returnValue;
    }

    public void log(){
        System.out.println("=======前置增强的日志============");
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dstr=sdf.format(d);
        System.out.println("执行时间为："+dstr);
        System.out.println("=======前置增强的日志结束=======");
    }
}

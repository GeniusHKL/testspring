package com.yc;

import com.yc.biz.StudentBizImpl;

public class Test1 {
    public static void main(String[] args) {
        StudentBizImpl sbi=new StudentBizImpl();
        LogAspectCglib lc=new LogAspectCglib(sbi);


        //生产代理
        Object obj=lc.createProxy(); //obj.toString()
        if(obj instanceof StudentBizImpl){
            StudentBizImpl s=(StudentBizImpl) obj;
            s.find("张三");
            s.update("李四");
            s.add("王五");
        }

    }
}

package com.yc;

import com.yc.biz.StudentBiz;
import com.yc.biz.StudentBizImpl;

public class Test1 {
    public static void main(String[] args) {
        StudentBiz target=new StudentBizImpl(); //Spring进行ioc
        LogAspect la=new LogAspect(target);

        Object obj=la.createProxy();  //obj就是代理对象
        //System.out.println(obj);
        if(obj instanceof StudentBiz){
            StudentBiz sb=(StudentBiz) obj;
            sb.find("张三"); //jvm 会感到这个sb是一个proxy  jvm会调用Proxy中的invoke
            sb.add("李四");
            sb.update("王五");
        }

    }
}

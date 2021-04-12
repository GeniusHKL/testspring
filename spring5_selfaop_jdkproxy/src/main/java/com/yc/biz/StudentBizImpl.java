package com.yc.biz;

public class StudentBizImpl implements StudentBiz{
    @Override
    public int add(String name) {
        System.out.println("StudentBiz的add方法"+name);
        return 100;
    }

    @Override
    public void update(String name) {
        System.out.println("StudentBiz的update方法"+name);

    }

    @Override
    public String find(String name) {
        System.out.println("StudentBiz的find方法"+name);
        return name+"++++";
    }
}

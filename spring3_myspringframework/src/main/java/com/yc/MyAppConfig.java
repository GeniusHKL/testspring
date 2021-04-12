package com.yc;

import com.yc.bean.HelloWorld;
import com.yc.springframework.stereotype.MyBean;
import com.yc.springframework.stereotype.MyComponentScan;
import com.yc.springframework.stereotype.MyConfiguration;

@MyConfiguration
@MyComponentScan(basePackages = {"com.yc.dao","com.yc.biz"})
public class MyAppConfig {

    @MyBean
    public HelloWorld hw(){
        return new HelloWorld();
   }

}

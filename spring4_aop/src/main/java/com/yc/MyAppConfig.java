package com.yc;

import com.yc.biz.StudentBizImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.yc"})
@EnableAspectJAutoProxy  //启用aspectJ支持
public class MyAppConfig {

}

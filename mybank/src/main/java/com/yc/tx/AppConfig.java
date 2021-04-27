package com.yc.tx;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@ComponentScan(basePackages = {"com.yc"})
@EnableTransactionManagement   //启用事务管理器
public class AppConfig {

    //创建数据源（是否采用连接池 c3p0 dbcp druid...



    @Bean  //@Bean的优先级高于@Component  创建事务管理器
    public TransactionManager DataSourceTransactionManager(DataSource ds){
        return  new DataSourceTransactionManager(ds);
    }
}

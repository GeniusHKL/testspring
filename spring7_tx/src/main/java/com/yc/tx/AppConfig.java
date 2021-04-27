package com.yc.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;

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
    @Bean
    @Profile("test")
    public DataSource dateSource() throws PropertyVetoException {
        DataSource ds=new ComboPooledDataSource();
        //
        ( (ComboPooledDataSource) ds).setDriverClass("com.mysql.jdbc.Driver");
        ( (ComboPooledDataSource) ds).setJdbcUrl("jdbc:mysql://localhost:3306/testBank");
        ( (ComboPooledDataSource) ds).setUser("root");
        ( (ComboPooledDataSource) ds).setPassword("a");
        return ds;
    }
    @Bean
    @Profile("prod")
    public DataSource dateSource2() throws PropertyVetoException {
        DataSource ds=new ComboPooledDataSource();
        //
        ( (ComboPooledDataSource) ds).setDriverClass("com.mysql.jdbc.Driver");
        ( (ComboPooledDataSource) ds).setJdbcUrl("jdbc:mysql://localhost:3306/testBank2");
        ( (ComboPooledDataSource) ds).setUser("root");
        ( (ComboPooledDataSource) ds).setPassword("a");
        return ds;
    }



    @Bean  //@Bean的优先级高于@Component  创建事务管理器
    public TransactionManager DataSourceTransactionManager(DataSource ds){
        return  new DataSourceTransactionManager(ds);
    }
}

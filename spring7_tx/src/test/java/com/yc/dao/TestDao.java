package com.yc.dao;

import com.yc.tx.AppConfig;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TestDao{
    @Autowired
    private DataSource dataSource;
    @Test
    public void testDataSource() throws SQLException {
        Assert.assertNotNull(dataSource);
        System.out.println(dataSource.getConnection());
    }
}

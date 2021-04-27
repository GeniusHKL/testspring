package com.yc.dao;

import com.yc.tx.AppConfig;
import com.yc.tx.bean.Accounts;
import com.yc.tx.dao.AccountsDaoImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TestDao{
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AccountsDaoImpl accountsDao;
    @Test
    public void testDataSource() throws SQLException {
        Assert.assertNotNull(dataSource);
        try{
            System.out.println(dataSource.getConnection());
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    @Test
    public void testAccountDaoImpl() throws SQLException {
        Assert.assertNotNull(accountsDao);


    }

    @Test
    public void testOpenAccount() throws SQLException {
        Accounts a=new Accounts();
        a.setBalance(10.0);
        int aid=accountsDao.saveAccount(a);
        System.out.println("开户成功，id为"+aid);

    }
    @Test
    public void testFindAll() throws SQLException {
        List<Accounts> list=this.accountsDao.findAll();
        System.out.println(list);
    }
    @Test
    public void testFindById() throws SQLException {
        Accounts a=this.accountsDao.findAccount(4);
        System.out.println(a);
    }

    @Test
    public void testDelete() throws SQLException {
        this.accountsDao.delete(11);
    }

    @Test
    public void testUpdate() throws SQLException {
        Accounts a=new Accounts();
        a.setAccountId(4);
        a.setBalance(20.0);
        this.accountsDao.updateAccount(a);
    }
}

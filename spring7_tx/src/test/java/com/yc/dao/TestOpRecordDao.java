package com.yc.dao;

import com.yc.tx.AppConfig;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.bean.OpType;
import com.yc.tx.dao.OpRecordDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TestOpRecordDao {
    @Autowired
    private OpRecordDao accountsDao;

    @Test
    public void testSave(){
        OpRecord opRecord=new OpRecord();
        opRecord.setAccountid(1);
        opRecord.setOpmoney(1.0);
        opRecord.setOptype(OpType.deposit.getName());
        opRecord.setOptime( new Timestamp(new Date().getTime()));
        opRecord.setTransferid(" ");
        accountsDao.saveOpRecord(opRecord);
    }

    @Test
    public void testAll(){
        List<OpRecord> list=accountsDao.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void testFindById(){
        List<OpRecord> list=accountsDao.findByAccountid(1);
        Assert.assertNotEquals(0,list.size());
    }

}

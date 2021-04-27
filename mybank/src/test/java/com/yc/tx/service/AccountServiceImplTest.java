package com.yc.tx.service;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpType;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {
    @Autowired
    private AccountService accountService;

    @Test
    @Transactional
    public void testOpenAccount(){
        Integer accountid=accountService.openAccount(new Accounts(),1);
        System.out.println(accountid);
        Assert.assertNotNull(accountid);
    }
    @Test
    public void testDeposite(){
        Accounts a=new Accounts();
        a.setAccountId(1);
        Accounts aa=accountService.deposite(a,999, OpType.deposit.getName(), null);
        System.out.println(aa);
    }
    @Test
    public void testWithdraw(){
        Accounts a=new Accounts();
        a.setAccountId(1);
        Accounts aa=accountService.withdraw(a,1, OpType.withdraw.getName(), null);
        System.out.println(aa);
    }

    @Test
    public void testTransfer(){
        Accounts out=new Accounts();
        out.setAccountId(1);

        Accounts inA=new Accounts();
        inA.setAccountId(2);

        this.accountService.transfer(inA,out,5);
    }

    @Test
    public void testShowBalance(){
        Accounts a=new Accounts();
        a.setAccountId(1);
        a=this.accountService.showBalance(a);
        System.out.println(a);
    }

    @Test
    public void testFindById(){

    }
}
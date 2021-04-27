package com.yc.tx.service;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;

import java.util.List;

public interface AccountService {

    public Integer openAccount(Accounts account,double money);

    public Accounts deposite(Accounts account, double money,String optype, String transferid);

    public Accounts withdraw(Accounts account,double money,String optype, String transferid);

    public Accounts transfer(Accounts inAccount,Accounts outAccount,double money);

    public Accounts showBalance(Accounts account);

    public List<OpRecord> findById(Accounts account);
}

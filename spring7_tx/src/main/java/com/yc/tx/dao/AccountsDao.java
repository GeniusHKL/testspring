package com.yc.tx.dao;

import com.yc.tx.bean.Accounts;

import java.util.List;

public interface AccountsDao {

    public int saveAccount(Accounts account) ;

    public Accounts updateAccount(Accounts account);

    public Accounts findAccount(int accountid);

    public List<Accounts> findAll();

    public void delete(int accountid);
}

package com.yc.tx.controllers;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpType;
import com.yc.tx.service.AccountService;
import com.yc.tx.vo.AccountVO;
import com.yc.tx.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@Api(description = "银行账户操作接口",tags = {"账户操作接口","控制层"})
public class AccountsController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/openAccounts",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "开户",notes = "根据账号，存款金额发出存款操作，返回操作完成后的新的余额")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "money",value = "存款余额",required = true))

    public @ResponseBody ResultVO openAccounts(AccountVO accountVO){
        log.debug("用户请求开户,存入"+accountVO.getMoney());
        ResultVO resultVO=new ResultVO();
        try{
            Accounts a=new Accounts();
            double money=1;
            if(accountVO.getMoney()!=null||accountVO.getMoney()>=0){
                money=accountVO.getMoney();
            }
            Integer id=accountService.openAccount(a,money);
            a.setAccountId(id);
            a.setBalance(money);

            resultVO.setCode(1);
            resultVO.setData(a);
        }catch (Exception e){
            e.printStackTrace();
            resultVO.setCode(0);

            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }

    @RequestMapping(value = "/deposit",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "存款",notes = "根据账号，存款金额发出存款操作，返回操作完成后的新的余额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId",value = "存款账号",required = true),
            @ApiImplicitParam(name = "money",value = "存款余额",required = true)})

    public @ResponseBody ResultVO deposite(AccountVO accountVO){
        ResultVO resultVO=new ResultVO();
        Accounts a=new Accounts();
        a.setAccountId(accountVO.getAccountId());
        try{
            a=accountService.deposite(a,accountVO.getMoney(), OpType.deposit.getName(), null);
            resultVO.setData(a);
            resultVO.setCode(1);

        }  catch (Exception e){
        e.printStackTrace();
        resultVO.setCode(0);

        resultVO.setMsg(e.getMessage());
    }
        return resultVO;
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    @ApiOperation(value = "取款", notes = "根据账户编号及金额来完成取款操作，注意此时的金额表示要取的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "账户编号", required = true ),
            @ApiImplicitParam(name = "money", value = "操作金额", required = true)})
    public @ResponseBody ResultVO withdraw(AccountVO accountVO){
        ResultVO resultVO=new ResultVO();
        Accounts a=new Accounts();
        a.setAccountId(accountVO.getAccountId());
        try{
            a=accountService.withdraw(a,accountVO.getMoney(), OpType.deposit.getName(), null);
            resultVO.setData(a);
            resultVO.setCode(1);

        }  catch (Exception e){
            e.printStackTrace();
            resultVO.setCode(0);

            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    @ApiOperation(value = "转账", notes = "根据账户编号及金额, 对方接收账号来完成转账操作，注意此时的金额表示要取的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "自己账户编号", required = true),
            @ApiImplicitParam(name = "money", value = "转账金额", required = true),
            @ApiImplicitParam(name = "inAccountId", value = "对方接收账号", required = true)})
    public @ResponseBody
    ResultVO transfer(AccountVO accountVO) {
        Accounts inAccount = new Accounts();
        inAccount.setAccountId(accountVO.getInAccountId());
        Accounts outAccount = new Accounts();
        outAccount.setAccountId(accountVO.getAccountId());
        ResultVO rv = new ResultVO();
        try {
            Accounts a = accountService.transfer(inAccount, outAccount, accountVO.getMoney());
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception ex) {
            ex.printStackTrace();
            rv.setCode(0);
            rv.setMsg(ex.getMessage());
        }
        return rv;
    }
    @RequestMapping(value = "/showBalance", method = RequestMethod.POST)
    @ApiOperation(value = "查询", notes = "根据账户编号查询余额")
    @ApiImplicitParams(@ApiImplicitParam(name = "accountId", value = "自己账户编号", required = true))
    public @ResponseBody ResultVO showBalance(AccountVO accountVO){
        Accounts a=new Accounts();
        ResultVO rv = new ResultVO();
        a.setAccountId(accountVO.getAccountId());
        try {
            a=accountService.showBalance(a);
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception ex) {
            ex.printStackTrace();
            rv.setCode(0);
            rv.setMsg(ex.getMessage());
        }
        return rv;
    }

}

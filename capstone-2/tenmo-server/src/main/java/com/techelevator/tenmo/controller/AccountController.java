package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.exceptions.NotFoundException;
import com.techelevator.tenmo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class AccountController {

    @Autowired
    private final AccountDao accountDao;

    public AccountController(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    //List all accounts
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/accounts")
    public List<Account> listAll(){
        return accountDao.listAll();
    }

    //Get an account by the id
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/account/{accountId}")
    public Account getUserAccount(@PathVariable Long accountId) throws NotFoundException {
        return accountDao.getUserAccount(accountId);
    }

    //Get an account balance by the id
    @GetMapping(path = "/account/balance/{accountId}")
    public BigDecimal getUserBalance(@PathVariable Long accountId) throws NotFoundException {
        return accountDao.getUserBalance(accountId);
    }

    //Get an accountId for balance using userId
    @GetMapping(path = "/user/account/{userId}")
    public Long getAccountIdWithUserId(@PathVariable Long userId) throws NotFoundException {
        return accountDao.getAccountIdWithUserId(userId);
    }

    //Updates our balance
    @PreAuthorize("permitAll")
    @PutMapping(path = "/account/update/balance/{balance}/{accountId}")
    public void updateBalance(@PathVariable BigDecimal balance, @PathVariable Long accountId){
        accountDao.updateBalance(balance, accountId);
    }


}

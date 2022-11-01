package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exceptions.NotFoundException;
import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    List<Account> listAll();

    Account getUserAccount(Long accountId) throws NotFoundException;

    BigDecimal getUserBalance(Long accountId) throws NotFoundException;

    Long getAccountIdWithUserId(Long userId) throws NotFoundException;

    void updateBalance(BigDecimal newBalance, Long accountToUpdate);
}

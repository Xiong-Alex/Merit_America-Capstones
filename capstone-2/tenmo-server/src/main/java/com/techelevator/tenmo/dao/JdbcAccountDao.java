package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exceptions.NotFoundException;
import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcAccountDao implements AccountDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> listAll(){
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT user_id, account_id, balance FROM account";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while (rowSet.next())
        {
            accounts.add(mapRowToAccount(rowSet));
        }
        return accounts;
    }

    @Override
    public Account getUserAccount(Long accountId) throws NotFoundException {
        Account account = null;
        String sql = "SELECT user_id, account_id, balance FROM account WHERE account_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, accountId);

        if (rowSet.next())
        {
            return mapRowToAccount(rowSet);
        }

        throw new NotFoundException("Account " + accountId + " was not found.");
    }

    @Override
    public BigDecimal getUserBalance(Long accountId) throws NotFoundException {
        Account account = null;
        String sql = "SELECT user_id, account_id, balance FROM account WHERE account_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, accountId);

        if (rowSet.next())
        {
            account = mapRowToAccount(rowSet);
            return account.getBalance();
        }

        throw new NotFoundException("Balance for " + accountId + " was not found.");
    }

    @Override
    public Long getAccountIdWithUserId(Long userId) throws NotFoundException {
        Account account = null;
        String sql = "SELECT user_id, account_id, balance FROM account WHERE user_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        if (rowSet.next())
        {
            account = mapRowToAccount(rowSet);
            return account.getUserId();
        }

        throw new NotFoundException("User " + userId + " was not found.");
    }

    @Override
    public void updateBalance(BigDecimal newBalance, Long userId) {
        String sql = "UPDATE account SET balance = ? WHERE account_id = ?";
        jdbcTemplate.update(sql, newBalance, userId);
    }


    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccountId(rs.getLong("user_id"));
        account.setUserId(rs.getLong("account_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }
}

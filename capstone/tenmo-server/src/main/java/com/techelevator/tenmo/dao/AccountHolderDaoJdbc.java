package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.AccountHolder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountHolderDaoJdbc implements AccountHolderDao{

    private JdbcTemplate jdbcTemplate;

    public AccountHolderDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<AccountHolder> getListOfOtherAccountHolders(int accountId) {
        List<AccountHolder> accountHolders = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM account " +
                "JOIN tenmo_user on tenmo_user.user_id = account.user_id " +
                "WHERE account_id != ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, accountId);

        while (rowSet.next()) {
            AccountHolder accountHolder = mapRowToAccountHolder(rowSet);
            accountHolders.add(accountHolder);
        }
        return accountHolders;
    }

    @Override
    public AccountHolder getAccountHolderById(int accountId) {
        AccountHolder accountHolder = new AccountHolder();
        String sql = "SELECT * " +
                "FROM account " +
                "JOIN tenmo_user on tenmo_user.user_id = account.user_id;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, accountId);

        if (rowSet.next()) {
            accountHolder = mapRowToAccountHolder(rowSet);
        }
        return accountHolder;
    }

    public AccountHolder mapRowToAccountHolder(SqlRowSet rowSet) {
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setAccountId(rowSet.getInt("account_id"));
        accountHolder.setUsername(rowSet.getString("username"));
        accountHolder.setBalance(rowSet.getBigDecimal("balance"));
        return accountHolder;
    }
}
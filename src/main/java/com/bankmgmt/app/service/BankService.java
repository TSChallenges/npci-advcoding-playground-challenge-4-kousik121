package com.bankmgmt.app.service;

import com.bankmgmt.app.entity.Account;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.*;

@Service
public class BankService {

    private List<Account> accounts = new ArrayList<>();
    private Integer currentId = 1;

    public Account createAccount(Account account) {
        account.setId(currentId);
        currentId += 1;
        account.setBalance(0.0);
        accounts.add(account);
        return account;
    }
    

    public List<Account> getAllAccounts() {
        return accounts;
    }
    

    public Account getAccountById(Integer id) {
        for (Account a: accounts) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return  null;
    }
    

    public Account depositAmount(Integer id, Double amount) {
        Account a = getAccountById(id);
        Double previous_balance = a.getBalance();
        a.setBalance(previous_balance + amount);
        return a;
    }
    

    public Account withdrawAmount(Integer id, Double amount) {
        Account a = getAccountById(id);
        Double previousBalance = a.getBalance();
        if (amount > previousBalance) {
            return null;
        }
        a.setBalance(previousBalance - amount);
        return a;
    }
    

    public List<Account> transferAmount(Integer fromId, Integer toId, Double amount) {
        Account fromAccount = getAccountById(fromId);
        Account toAccount = getAccountById(toId);
        if (fromAccount != null && toAccount != null) {
            if(amount <= fromAccount.getBalance()) {
                List<Account> resultAccounts = new ArrayList<>();
                Double previousFromBalance = fromAccount.getBalance();
                Double previousToBalance = toAccount.getBalance();
                fromAccount.setBalance(previousFromBalance - amount);
                toAccount.setBalance(previousToBalance + amount);
                resultAccounts.add(fromAccount);
                resultAccounts.add(toAccount);
                return resultAccounts;
            }
        }
        return null;
    }
    
    
    public String deleteAccount(Integer id) {
        Account a = getAccountById(id);
        if (a != null) {
            accounts.remove(a);
            return "Delete successfully";
        }
        return "No account found";
    }


}

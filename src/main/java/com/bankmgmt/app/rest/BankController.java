package com.bankmgmt.app.rest;

import com.bankmgmt.app.entity.Account;
import com.bankmgmt.app.entity.Amount;
import com.bankmgmt.app.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("")
    public Account createAccount(@RequestBody Account account) {
        return bankService.createAccount(account);
    }
    

    @GetMapping("")
    public List<Account> geAllAccounts() {
        return bankService.getAllAccounts();
    }
    

    @GetMapping("{id}")
    public Account getAccountById(@PathVariable("id") Integer id) {
        return bankService.getAccountById(id);
    }
    

    @PostMapping("{id}/deposit")
    public Account depositAmount(@PathVariable("id") Integer id, @RequestBody Amount amount) {
        return bankService.depositAmount(id, amount.getAmount());
    }
    

    @PostMapping("{id}/withdraw")
    public Account withdrawAmount(@PathVariable("id") Integer id, @RequestBody Amount amount) {
        return bankService.withdrawAmount(id, amount.getAmount());
    }
    

    @PostMapping("transfer")
    public List<Account> transferAmount(@RequestParam("fromId") Integer fromId, @RequestParam("toId") Integer toId, @RequestParam("amount") Double amount) {
        return bankService.transferAmount(fromId, toId, amount);
    }
    

    @DeleteMapping("{id}")
    public ResponseEntity deleteAccount(@PathVariable("id") Integer id) {
        if (bankService.deleteAccount(id).equals("Delete successfully")) {
            return ResponseEntity.status(204).build();
        }
        else {
            return ResponseEntity.status(404).build();
        }
    }
    
    
}

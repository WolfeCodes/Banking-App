package com.wolfecodes.bankingapp.controller;

import com.wolfecodes.bankingapp.dto.AccountDto;
import com.wolfecodes.bankingapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
/*This is our BaseURL*/@RequestMapping("/api/accounts")


public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Add Account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody /*Automatically converts Json into the AccountDto object*/ AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountByID(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountByID(id);
        return ResponseEntity.ok(accountDto);
    }

    //Deposit Account REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }
}

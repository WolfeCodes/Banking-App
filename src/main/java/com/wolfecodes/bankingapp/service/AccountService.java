package com.wolfecodes.bankingapp.service;

import com.wolfecodes.bankingapp.dto.AccountDto;

import java.util.List;


public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountByID(Long id);
    AccountDto deposit(Long id, Double amount);
    AccountDto withdraw(Long id, Double amount);

    List<AccountDto> getAllAccounts();
}

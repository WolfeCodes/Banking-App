package com.wolfecodes.bankingapp.service.impl;

import com.wolfecodes.bankingapp.dto.AccountDto;
import com.wolfecodes.bankingapp.entity.Account;
import com.wolfecodes.bankingapp.mapper.AccountMapper;
import com.wolfecodes.bankingapp.repository.AccountRepository;
import com.wolfecodes.bankingapp.service.AccountService;
import org.springframework.stereotype.Service;

@Service

public class AccountServiceimpl implements AccountService {
    private AccountRepository accountRepository;


    public AccountServiceimpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountByID(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, Double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }
}

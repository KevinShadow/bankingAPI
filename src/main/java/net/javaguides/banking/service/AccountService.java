package net.javaguides.banking.service;

import java.util.List;

import net.javaguides.banking.controller.dto.AccountDto;
// import net.javaguides.banking.entity.Account;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccount();

    void deleteAccount(Long id);
}

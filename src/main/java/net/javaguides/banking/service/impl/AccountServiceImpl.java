package net.javaguides.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import net.javaguides.banking.exception.AccountException;

import org.springframework.stereotype.Service;

import net.javaguides.banking.controller.dto.AccountDto;
import net.javaguides.banking.controller.dto.mapper.AccountMapper;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto){
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id){

        Account account = accountRepository
            .findById(id)
            .orElseThrow(() -> new AccountException("Le compte n'existe pas"));


        return AccountMapper.mapToAccountDto(account);
    }


    @Override
    public AccountDto deposit(Long id, double amount){

        Account account = accountRepository
            .findById(id)
            .orElseThrow(() -> new AccountException("Le compte n'existe pas"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto((saveAccount));
    }

    @Override
    public AccountDto withdraw(Long id, double amount){

        Account account = accountRepository
            .findById(id)
            .orElseThrow(() -> new AccountException("Le compte n'existe pas"));

        if (account.getBalance() < amount) {
            throw new AccountException("compte insufisant");
        }
        double toatal = account.getBalance() - amount;
        account.setBalance(toatal);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccount(){
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(
            (account) -> AccountMapper.mapToAccountDto((account))
            ).collect(
                Collectors.toList()
            );
    }

    @Override
    public void deleteAccount(Long id){
        Account account = accountRepository
            .findById(id)
            .orElseThrow(() -> new AccountException("Le compte n'existe pas"));

        accountRepository.deleteById(id);
    }
}

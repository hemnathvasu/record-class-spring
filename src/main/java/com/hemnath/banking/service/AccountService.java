package com.hemnath.banking.service;

import com.hemnath.banking.dto.AccountDto;
import com.hemnath.banking.dto.AccountMapper;
import com.hemnath.banking.entity.Account;
import com.hemnath.banking.globalexception.AccountNotFoundException;
import com.hemnath.banking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountDto createAccount(AccountDto accountDto){
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.accountToDto(savedAccount);
    }

    public AccountDto getById(Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(()->new AccountNotFoundException("Account Not found :"+id));

        return AccountMapper.accountToDto(account);
    }

    public AccountDto addDeposit(Long id, double amount){
        Account account = accountRepository.findById(id)
                .orElseThrow(()->new AccountNotFoundException("Account Not found :"+id));

        double total = account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);

        return AccountMapper.accountToDto(savedAccount);

    }

    public List<AccountDto> getAllAccount(){
        List<Account> accountsList = accountRepository.findAll();

        return accountsList.stream()
                .map(AccountMapper::accountToDto)
                .toList();
    }

    public AccountDto withDrawAmount(Long id, double amount){
        Account account = accountRepository.findById(id)
                .orElseThrow(()->new AccountNotFoundException("Account Not found :"+id));

        if(amount > account.getBalance()){
            throw new RuntimeException("Insufficient balance");
        }
        double balance =  account.getBalance()-amount;
        account.setBalance(balance);

        Account savedAccount=accountRepository.save(account);
        return AccountMapper.accountToDto(savedAccount);
    }

    public String deleteById(Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(()->new AccountNotFoundException("Account Not found :"+id));

        accountRepository.deleteById(id);
        return "Delete an account "+ id;
    }

}

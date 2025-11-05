package com.hemnath.banking.dto;

import com.hemnath.banking.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        return new Account(
                accountDto.id(),
                accountDto.name(),
                accountDto.balance()
        );
    }

    public static AccountDto accountToDto(Account account){
        return new AccountDto(
                account.getId(),
                account.getName(),
                account.getBalance()
        );
    }
}

package com.hemnath.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class AccountDto {
//    private Long id;
//    private String name;
//    private double balance;
//
//}


public record AccountDto(Long id,
                         String name,
                         double balance) {
}
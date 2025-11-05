package com.hemnath.banking.controller;

import com.hemnath.banking.dto.AccountDto;
import com.hemnath.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDto> getById(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getById(id),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccount(){
        return new ResponseEntity<>(accountService.getAllAccount(),HttpStatus.OK);
    }

    @PutMapping("{id}/deposit")
    public ResponseEntity<AccountDto> addDeposit(@PathVariable Long id,
                                                 @RequestBody Map<String,Double> request){

        Double amount = request.get("amount");
        return new ResponseEntity<>(accountService.addDeposit(id,amount),HttpStatus.OK);
    }

    @PutMapping("{id}/withdraw")
    public ResponseEntity<AccountDto> withDraw(@PathVariable Long id,
                                                 @RequestBody Map<String,Double> request){

        Double amount = request.get("amount");
        return new ResponseEntity<>(accountService.withDrawAmount(id,amount),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(accountService.deleteById(id),HttpStatus.OK);
    }
}

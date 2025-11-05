package com.hemnath.banking.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResponseEntity<AccountNotFoundError> handleNotFound(AccountNotFoundException exception){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        AccountNotFoundError error = new AccountNotFoundError(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                LocalDateTime.now().format(formatter)  // ✅ Correct usage
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

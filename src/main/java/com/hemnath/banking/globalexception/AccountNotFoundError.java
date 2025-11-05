package com.hemnath.banking.globalexception;


public record AccountNotFoundError(int status,
                                   String message,
                                   String time) {
}

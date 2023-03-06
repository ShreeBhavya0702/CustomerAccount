package com.Bank.CustomerAccount.Exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message)
    {
        super(message);
    }
}

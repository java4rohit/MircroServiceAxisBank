package com.java4rohit.accounts.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class CustomerAlreadyExistsException extends  RuntimeException {


    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}

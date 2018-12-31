package com.tb.bimo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BasketProductIdNotFoundException extends RuntimeException {

    public BasketProductIdNotFoundException(String message) {
        super(message);
    }
}

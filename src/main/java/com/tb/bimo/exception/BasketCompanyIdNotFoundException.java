package com.tb.bimo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BasketCompanyIdNotFoundException extends RuntimeException {

    public BasketCompanyIdNotFoundException(String message) {
        super(message);
    }
}

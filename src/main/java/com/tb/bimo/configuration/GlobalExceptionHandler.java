package com.tb.bimo.configuration;

import com.tb.bimo.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource does not exist")
    @ExceptionHandler(ResourceNotFoundException.class)
    public void handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error("Resource does not exist", e);
        log.info(e.getMessage());
    }
/*
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Server Error Occurred")
    @ExceptionHandler(InternalServerErrorException.class)
    public void handleInternalServerErrorException(InternalServerErrorException e) {
        log.error("Internal Server Error", e);
        log.info(e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Store with given dms id already exists.")
    @ExceptionHandler(StoreAlreadyExistsException.class)
    public void handleStoreAlreadyExistsException(StoreAlreadyExistsException e) {
        log.error("Store Already Exist Exception", e);
        log.info(e.getMessage());
    }
*/
}

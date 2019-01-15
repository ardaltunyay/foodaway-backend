package com.tb.foodaway.configuration;

import com.tb.foodaway.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException e) {
        return handleError("Not Found", e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BasketProductIdNotFoundException.class)
    public ResponseEntity<ApiError> handleBasketProductIdNotFoundException(BasketProductIdNotFoundException e) {
        return handleError("Bad Request", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BasketCompanyIdNotFoundException.class)
    public ResponseEntity<ApiError> handleBasketCompanyIdNotFoundException(BasketCompanyIdNotFoundException e) {
        return handleError("Bad Request", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException e) {
        return handleError("Bad Request", e.getMessage(), HttpStatus.BAD_REQUEST);
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
    private ResponseEntity<ApiError> handleError(String error, String message, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();

        ApiError apiError = ApiError.builder()
                .status(status.value())
                .error(error)
                .message(message)
                .build();
        return new ResponseEntity<>(apiError, headers, status);
    }
}

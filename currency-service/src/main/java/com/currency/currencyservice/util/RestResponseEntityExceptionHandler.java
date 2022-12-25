package com.currency.currencyservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
    @ExceptionHandler(value
            = { IllegalStateException.class })
    protected ResponseEntity<Object> handleIllegalState(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse =
                "Error while converting currencies. The name of the currencies is not set correctly, or the rate is not set correctly for currency.";
        logger.error(ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value
            = { NumberFormatException.class })
    protected ResponseEntity<Object> handleNumberFormat(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse =
                "Error while converting currencies. Initial value is not correct.";
        logger.error("Initial value is not correct ");
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}

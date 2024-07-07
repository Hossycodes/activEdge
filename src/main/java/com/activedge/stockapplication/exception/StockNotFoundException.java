package com.activedge.stockapplication.exception;

import org.springframework.http.HttpStatus;

public class StockNotFoundException extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public StockNotFoundException(String message) {
        super(message);
    }

    public StockNotFoundException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}

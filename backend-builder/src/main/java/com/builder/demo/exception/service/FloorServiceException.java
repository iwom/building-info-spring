package com.builder.demo.exception.service;

import org.springframework.http.HttpStatus;

public class FloorServiceException extends RuntimeException {
    public static final long serialVersionUID = 1L;
    private HttpStatus httpStatus;

    public FloorServiceException(String message) {
        super(message);
    }

    public FloorServiceException(String message, HttpStatus statusCode) {
        this(message);
        this.httpStatus = statusCode;

    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}


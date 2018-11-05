package com.builder.demo.exception.service;

import org.springframework.http.HttpStatus;

public class BuildingServiceException extends RuntimeException {
    public static final long serialVersionUID = 1L;
    private HttpStatus httpStatus;

    public BuildingServiceException(String message) {
        super(message);
    }

    public BuildingServiceException(String message, HttpStatus statusCode) {
        this(message);
        this.httpStatus = statusCode;

    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}


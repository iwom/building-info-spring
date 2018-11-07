package com.builder.demo.model.error;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    MISSING_FIELD("Required field is missing"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    RECORD_NOT_FOUND("Record was not found"),
    RECORD_NOT_CREATED("Could not create record"),
    RECORD_NOT_DELETED("Could not delete record"),
    RECORD_NOT_UPDATED("Could not update record"),
    BUILDING_NOT_EXIST("Requested building does not exist"),
    FLOOR_NOT_EXIST("Requested floor does not exist"),
    INTERNAL_SERVER_ERROR("Internal server error");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
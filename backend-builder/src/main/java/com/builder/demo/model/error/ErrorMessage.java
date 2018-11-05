package com.builder.demo.model.error;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorMessage {
    private String stringTimestamp;
    private String errorMessage;

    public ErrorMessage() {
    }

    public ErrorMessage(String errorMessage) {
        this(new Date(), errorMessage);
    }

    public ErrorMessage(Date timestamp, String errorMessage) {
        this.stringTimestamp = timestamp.toString();
        this.errorMessage = errorMessage;
    }
}

package ru.trofimov.bikeroutesadmin.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ConnectionException {
    private final HttpStatus status;
    private final String message;
    private final String detail;
    private final ZonedDateTime timestamp;

    public ConnectionException(HttpStatus status, String message, String detail) {
        this.status = status;
        this.message = message;
        this.detail = detail;
        timestamp = ZonedDateTime.now();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}

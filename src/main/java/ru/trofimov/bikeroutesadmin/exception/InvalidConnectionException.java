package ru.trofimov.bikeroutesadmin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidConnectionException extends RuntimeException {

    public InvalidConnectionException() {
        super();
    }

    public InvalidConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidConnectionException(String message) {
        super(message);
    }

    public InvalidConnectionException(Throwable cause) {
        super(cause);
    }
}

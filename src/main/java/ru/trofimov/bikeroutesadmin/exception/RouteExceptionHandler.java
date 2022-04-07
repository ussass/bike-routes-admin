package ru.trofimov.bikeroutesadmin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RouteExceptionHandler {

    @ExceptionHandler(InvalidConnectionException.class)
    ResponseEntity<ConnectionException> invalidConnection(HttpServletRequest request, InvalidConnectionException e) {
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        ConnectionException exception = new ConnectionException(
                status,
                e.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exception, status);
    }

    @ExceptionHandler(InvalidAuthorizationException.class)
    ResponseEntity<ConnectionException> invalidLoginOrPassword(HttpServletRequest request, InvalidAuthorizationException e) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        ConnectionException exception = new ConnectionException(
                status,
                e.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exception, status);
    }
}

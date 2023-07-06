package com.example.gameproject.exception;

public class UsernameFoundException extends Exception {

    public UsernameFoundException() {
        super();
    }

    public UsernameFoundException(String message) {
        super(message);
    }

    public UsernameFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameFoundException(Throwable cause) {
        super(cause);
    }

    protected UsernameFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

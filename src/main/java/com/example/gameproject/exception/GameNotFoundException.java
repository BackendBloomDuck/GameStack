package com.example.gameproject.exception;

public class GameNotFoundException extends Exception{
    public GameNotFoundException() {
        super();
    }

    public GameNotFoundException(String message) {
        super(message);
    }

    public GameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameNotFoundException(Throwable cause) {
        super(cause);
    }

    protected GameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

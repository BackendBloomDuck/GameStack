package com.example.gameproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorMessage> handleMaxSizeException(MaxUploadSizeExceededException e, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.EXPECTATION_FAILED, "File too large! (Max:" + e.getMaxUploadSize() +")");
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
    }

    @ExceptionHandler(GameNotFoundException.class)
    public  ResponseEntity<ErrorMessage> handleGameNotFoundException(GameNotFoundException e, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public  ResponseEntity<ErrorMessage> handleUserNotFoundException(UserNotFoundException e, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}

package com.example.management;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.management.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Void> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 for no-body
    }

    // Handle other general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 for other errors
    }
}

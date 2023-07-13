package com.example.weatherforecast.error;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
;

@ControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value= NullPointerException.class)
    public ResponseEntity<?> NullPointerExceptionHandler(NullPointerException exception){
        System.out.println(exception.getMessage());
        return ResponseEntity.status(500).body("Bad request or Location not found");
    }
}

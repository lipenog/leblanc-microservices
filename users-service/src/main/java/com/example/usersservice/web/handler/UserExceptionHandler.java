package com.example.usersservice.web.handler;

import com.example.usersservice.exception.InvalidUsersDtoException;
import feign.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(InvalidUsersDtoException.class)
    public ResponseEntity<String> handle(InvalidUsersDtoException ex){
        return new ResponseEntity<>(ex.getViolations().stream().reduce("", (s1, s2) -> s1 + "\n" + s2).trim(), HttpStatus.BAD_REQUEST);
    }

}

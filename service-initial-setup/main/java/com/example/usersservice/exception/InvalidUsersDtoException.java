package com.example.usersservice.exception;

import java.util.List;

public class InvalidUsersDtoException extends InvalidDtoValidationException{
    public InvalidUsersDtoException(List<String> violations) {
        super(violations);
    }
}

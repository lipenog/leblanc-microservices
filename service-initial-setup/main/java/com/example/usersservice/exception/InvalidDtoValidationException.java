package com.example.usersservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@AllArgsConstructor
@Getter
public class InvalidDtoValidationException extends Exception{
    private List<String> violations;
}

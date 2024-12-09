package com.example.usersservice.web.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.List;
import java.util.Set;

public class DtoValidator {
    public static List<String> validateDto(Object dto){
        Validator validator;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
            Set<ConstraintViolation<Object>> violations = validator.validate(dto);
            return violations.stream().map(ConstraintViolation::getMessage).toList();
        }
    }
}

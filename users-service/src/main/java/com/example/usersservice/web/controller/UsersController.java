package com.example.usersservice.web.controller;

import com.example.usersservice.web.dto.UsersDTO;
import com.example.usersservice.web.service.UsersService;
import com.example.usersservice.web.utils.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/users")
    public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO usersDTO) {
        DtoValidator.validateDto(usersDTO).forEach(i -> System.out.println(i));
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}

package com.example.usersservice.web.controller;

import com.example.usersservice.exception.InvalidUsersDtoException;
import com.example.usersservice.web.dto.UsersDTO;
import com.example.usersservice.web.entity.Users;
import com.example.usersservice.web.service.UsersService;
import com.example.usersservice.web.utils.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/register")
    public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO usersDTO) throws InvalidUsersDtoException {
        // validates user dto
        List<String> violations = DtoValidator.validateDto(usersDTO);
        if(!violations.isEmpty()) throw new InvalidUsersDtoException(violations);

        // creates user
        Users users = usersService.createUser(usersDTO);
        return new ResponseEntity<>(new UsersDTO(users), HttpStatus.CREATED);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable Long id){
        Optional<Users> optionalUsers = usersService.getById(id);
        return optionalUsers.map(users -> ResponseEntity.ok(new UsersDTO(users))).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/users")
    public ResponseEntity<UsersDTO> searchUsers(){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
    @PutMapping("/users")
    public ResponseEntity<UsersDTO> updateUser(){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}

package com.example.usersservice.web.controller;

import com.example.usersservice.exception.InvalidUsersDtoException;
import com.example.usersservice.web.dto.UsersDTO;
import com.example.usersservice.web.dto.UsersPageDTO;
import com.example.usersservice.web.entity.Users;
import com.example.usersservice.web.service.UsersService;
import com.example.usersservice.web.utils.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @GetMapping("/users/identifier/{id}")
    public ResponseEntity<UsersDTO> getUserByIdentifier(@PathVariable String identifier){
        Optional<Users> optionalUsers = usersService.getByIdentifier(identifier);
        return optionalUsers.map(users -> ResponseEntity.ok(new UsersDTO(users))).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable Long id){
        Optional<Users> optionalUsers = usersService.getById(id);
        return optionalUsers.map(users -> ResponseEntity.ok(new UsersDTO(users))).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/users")
    public ResponseEntity<UsersPageDTO> searchUsers(@RequestParam(name = "content") String content, @RequestParam(name = "page") Integer page){
        Page<Users> usersPage = usersService.searchUsers(content, page);
        return ResponseEntity.ok(new UsersPageDTO(usersPage));
    }
    @PutMapping("/users")
    public ResponseEntity<UsersDTO> updateUser(@RequestBody UsersDTO usersDTO) throws InvalidUsersDtoException {
        // verify dto
        List<String> violations = DtoValidator.validateDto(usersDTO);
        if(!violations.isEmpty()) throw new InvalidUsersDtoException(violations);
        // search logged user
        String identifier = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> usersOptional = usersService.getByIdentifier(identifier);
        if(usersOptional.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // update data
        Users response = usersService.updateUser(usersDTO, usersOptional.get());
        return ResponseEntity.ok(new UsersDTO(response));
    }
}

package com.example.usersservice.web.controller;

import com.example.usersservice.web.dto.UsersDTO;
import com.example.usersservice.web.entity.Users;
import com.example.usersservice.web.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
public class CredentialsController {
    private final UsersService usersService;

    @Autowired
    public CredentialsController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/credentials")
    public CompletableFuture<ResponseEntity<UsersDTO>> credentialsGet(Authentication authentication) {
        return CompletableFuture
                .supplyAsync(
                        () -> {
                            Optional<Users> users = usersService.getByIdentifier(authentication.getName());
                            if(users.isEmpty()) throw new RuntimeException();
                            return ResponseEntity.ok(new UsersDTO(users.get()));
                        }
                );
    }

}

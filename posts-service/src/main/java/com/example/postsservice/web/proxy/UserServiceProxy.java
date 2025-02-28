package com.example.postsservice.web.proxy;

import com.example.postsservice.web.dto.UsersDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
@FeignClient(name = "user-service")
public interface UserServiceProxy {
    @GetMapping("/users/identifier/{identifier}")
    UsersDTO getUserByIdentifier(@PathVariable String identifier);
    @GetMapping("/users/{id}")
    UsersDTO getUserById(@PathVariable Long id);
}

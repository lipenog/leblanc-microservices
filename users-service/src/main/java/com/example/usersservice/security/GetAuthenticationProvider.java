package com.example.usersservice.security;

import com.example.usersservice.web.entity.Users;
import com.example.usersservice.web.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class GetAuthenticationProvider implements AuthenticationProvider {
    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public GetAuthenticationProvider(UsersService usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        String identifier = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        var optional = usersService.getByIdentifier(identifier);
        if(optional.isEmpty()) throw new RuntimeException("Invalid credentials");
        Users users = optional.get();
        if(!passwordEncoder.matches(pwd, users.getPassword())) throw new RuntimeException("Invalid credentials");
        return new UsernamePasswordAuthenticationToken(identifier, pwd, Collections.singletonList((GrantedAuthority) new SimpleGrantedAuthority("user")));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}

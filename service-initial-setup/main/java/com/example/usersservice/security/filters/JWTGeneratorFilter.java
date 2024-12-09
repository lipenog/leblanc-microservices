package com.example.usersservice.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static java.util.Objects.nonNull;

public class JWTGeneratorFilter extends OncePerRequestFilter {
    private final String JWT_HEADER;
    private final String JWT_KEY;

    public JWTGeneratorFilter(String JWT_HEADER, String JWT_KEY) {
        this.JWT_HEADER = JWT_HEADER;
        this.JWT_KEY = JWT_KEY;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (nonNull(authentication)) {
            String jwt = JWTUtil.buildJWT(authentication, JWT_KEY);

            response.setHeader(JWT_HEADER, "Bearer: " + jwt);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/credentials");
    }

}

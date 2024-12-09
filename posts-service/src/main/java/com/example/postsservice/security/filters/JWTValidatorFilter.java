package com.example.postsservice.security.filters;


import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static java.util.Objects.nonNull;

public class JWTValidatorFilter extends OncePerRequestFilter {
    private final String JWT_HEADER;
    private final String JWT_KEY;

    public JWTValidatorFilter(String jwtHeader, String jwtKey) {
        JWT_HEADER = jwtHeader;
        JWT_KEY = jwtKey;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(JWT_HEADER);

        if (nonNull(jwt)) {
            try {
                jwt = jwt.replaceFirst("Bearer: ", "");

                Authentication authentication = JWTUtil.decodeJWT(jwt, JWT_KEY);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JwtException e2){
                throw new RuntimeException("Jwt expired");
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return false;
    }

}

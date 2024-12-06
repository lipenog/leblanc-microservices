package com.example.usersservice.security.filters;


import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static java.util.Objects.nonNull;

public class JWTRefreshTokenFilter extends OncePerRequestFilter {
    private final String JWT_HEADER;
    private final String JWT_KEY;
    public JWTRefreshTokenFilter(String jwtHeader, String jwtKey) {
        JWT_HEADER = jwtHeader;
        JWT_KEY = jwtKey;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(JWT_HEADER);
        if (nonNull(jwt)) {
            jwt = jwt.replaceFirst("Bearer: ", "");
            try {
                Authentication authentication = JWTUtil.decodeJWT(jwt, JWT_KEY);

                String refreshToken = JWTUtil.buildJWT(authentication, JWT_KEY);

                response.setHeader(JWT_HEADER, "Bearer: " + refreshToken);
            } catch (JwtException e2){
                throw new RuntimeException("Jwt expired");
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/credentials");
    }

}

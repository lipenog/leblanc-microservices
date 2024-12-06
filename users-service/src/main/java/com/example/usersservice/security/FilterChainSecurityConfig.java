package com.example.usersservice.security;

import com.example.usersservice.security.filters.JWTGeneratorFilter;
import com.example.usersservice.security.filters.JWTRefreshTokenFilter;
import com.example.usersservice.security.filters.JWTValidatorFilter;
import com.example.usersservice.security.filters.LoginDetailsValidationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class FilterChainSecurityConfig {
    @Value("${security.jwt.header}")
    private String JWT_HEADER;
    @Value("${security.jwt.key}")
    private String JWT_KEY;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.configurationSource(new CorsConfig()))
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new LoginDetailsValidationFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTValidatorFilter(JWT_HEADER, JWT_KEY), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTRefreshTokenFilter(JWT_HEADER, JWT_KEY), JWTValidatorFilter.class)
                .addFilterAfter(new JWTGeneratorFilter(JWT_HEADER, JWT_KEY), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/users", "/users/**").authenticated()
                        .requestMatchers("/credentials").authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

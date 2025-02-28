package com.example.postsservice.security;

import com.example.postsservice.security.filters.JWTValidatorFilter;
import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
                .addFilterBefore(new JWTValidatorFilter(JWT_HEADER, JWT_KEY), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/posts").authenticated()
                        .requestMatchers("/posts/**").authenticated()
                        .requestMatchers("/search").authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null) {
                template.header("Authorization", authorizationHeader);
            }
        };
    }
}

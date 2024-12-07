package com.example.usersservice.security.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class JWTUtil {
    public static Authentication decodeJWT(String JWT, String JWT_KEY){
        SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(JWT)
                .getPayload();

        String username = String.valueOf(claims.get("email"));
        String authorities = (String) claims.get("authorities");

        return new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
    }

    public static String buildJWT(Authentication authentication, String JWT_KEY){
        SecretKey secretKey = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .issuer("Spring-Boot")
                .subject("JWT TOKEN")
                .claim("email", authentication.getName())
                .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 72000000))
                .signWith(secretKey).compact();
    }


    private static String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        List<String> authorities = collection.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return String.join(",", authorities);
    }
}

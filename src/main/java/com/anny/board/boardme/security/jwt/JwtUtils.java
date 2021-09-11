package com.anny.board.boardme.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiredTime}")
    private Long expiredTime;

    public String createToken(String username) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        Date current = new Date();
        Date expiredAt = (Date) current.clone();
        expiredAt.setTime(expiredAt.getTime() + expiredTime);

        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(username)
                .setIssuedAt(current)
                .setExpiration(expiredAt)
                .compact();
    }

    public String verifyJwtToken(String token) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return jws.getBody().getSubject();
    }
}

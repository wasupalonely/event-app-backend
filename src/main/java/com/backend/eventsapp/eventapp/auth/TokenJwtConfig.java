package com.backend.eventsapp.eventapp.auth;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class TokenJwtConfig {
    public final static SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public final static String PREFIX_TOKEN = "Bearer ";
    public final static String HEADER_AUTHORIZATION = "Authorization";

    public static String generateToken(String username, Map<String, ?> claims) {
        long expirationTimeInMillis = 3600000;
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTimeInMillis);

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .signWith(SECRET_KEY)
                .issuedAt(now)
                .expiration(expirationDate)
                .compact();
    }

    public static Claims validateToken(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

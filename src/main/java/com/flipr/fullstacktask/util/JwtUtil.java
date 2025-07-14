package com.flipr.fullstacktask.util;

// JWT library from Auth0
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    // Secret key used to sign the token (don't share this in production)
    private static final String SECRET_KEY = "flipr-secret-key";

    // Method to generate token from username
    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)  // payload: the user name
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiry
                .sign(Algorithm.HMAC256(SECRET_KEY)); // signed using secret
    }

    // Method to validate token and extract the username (subject)
    public String validateTokenAndRetrieveSubject(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY)) // same key must be used
                .build()
                .verify(token)
                .getSubject(); // returns the username if token is valid
    }
}

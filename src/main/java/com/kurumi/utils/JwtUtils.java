package com.kurumi.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

public class JwtUtils {

    private static String signature = "kurumi";
    private static long time = 1000 * 60 * 60 * 24;

    public static String createToken() {
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                // header
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                // payload
//                .claim("username", "tom")
//                .claim("role", "admin")
//                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                // signature
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
        return jwtToken;
    }

    public static boolean validateJwt(String token) {
        if(token == null) return false;
        JwtParser jwtParser = Jwts.parser();
        try {
            Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

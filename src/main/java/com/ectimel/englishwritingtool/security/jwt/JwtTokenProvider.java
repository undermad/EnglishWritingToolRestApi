package com.ectimel.englishwritingtool.security.jwt;

import com.ectimel.englishwritingtool.exception.ApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String secret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long expirationTime;

    private Key key() {
        // decode secret key and return as Key class
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateToken(Authentication authentication) {

        String username = authentication.getName();
        Date currentDate = new Date();

        // generate date 7 day old
        Date expirationDate = new Date(currentDate.getTime() + expirationTime);

        // return token
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(key())
                .compact();
    }



    public String getUsername(String token) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
        } catch (MalformedJwtException e) {
            throw new ApiException("Invalid token");
        } catch (ExpiredJwtException e) {
            throw new ApiException("Expired token");
        } catch (UnsupportedJwtException e){
            throw new ApiException("Unsupported JWT token");
        } catch (IllegalArgumentException e){
            throw new ApiException("JWT claims string is empty.");
        }

        return true;
    }
}

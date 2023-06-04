package com.ectimel.englishwritingtool.security.jwt;

import com.ectimel.englishwritingtool.exception.ApiException;
import com.ectimel.englishwritingtool.security.api.User;
import com.ectimel.englishwritingtool.security.api.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String secret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long expirationTime;

    private final UserRepository userRepository;

    public JwtTokenProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


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

    public Long getUserIdFromToken(String token) {
        if(!token.contains("Bearer")){
            throw new ApiException("Please, log-in first");
        }
        String email = getUsername(token.substring(7));
        Optional<User> userAsOptional = userRepository.findByUsernameOrEmail(email, email);
        if (userAsOptional.isEmpty()) {
            throw new ApiException("Please, log-in first.");
        }
        User user = userAsOptional.get();
        return user.getId();
    }
}

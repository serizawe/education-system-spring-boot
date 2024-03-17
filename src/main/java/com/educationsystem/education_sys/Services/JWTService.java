package com.educationsystem.education_sys.Services;

import com.educationsystem.education_sys.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {
    private final String SECRET_KEY = "a1ce02df63e3bc12e2b47349aaad08b0f91500be45a6f873319a1a2ee6d1b5bf";
    public String generateToken(User user){
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000 ))
                .signWith(getSigningKey())
                .compact();
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsResolvers){
        Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    public String extractUserName(String token){
        return extractClaim(token,Claims::getSubject);

    }

    public boolean isValid(String token, UserDetails user){
        String username = extractUserName(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] key = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }
}

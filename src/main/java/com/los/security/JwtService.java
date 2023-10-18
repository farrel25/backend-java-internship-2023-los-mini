package com.los.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value(value = "${auth.secret-key}")
    private String secretKey;

    @Value(value = "${auth.access-token.expiration}")
    private Long accessTokenExpiration;

    public String extractUsername(String token) {
        return this.extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateAccessToken(UserDetails userDetails) {
        return this.generateAccessToken(new HashMap<>(), userDetails);
    }

    public String generateAccessToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return this.buildToken(extraClaims, userDetails, accessTokenExpiration);
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, Long expiration) {
        return Jwts
                .builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(this.getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = this.extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return this.extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return this.extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(this.getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

package com.edutech.progressive.jwt;

import com.edutech.progressive.entity.Customers;
import com.edutech.progressive.repository.CustomerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final CustomerRepository customerRepository;

    @Autowired
    public JwtUtil(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Must be at least 64 characters/bytes for HS512
    private final String secret = "BankSafeJwtSecretKeyForHS512MustBeAtLeastSixtyFourCharactersLong123456789";

    private final long expirationMs = 86400 * 1000; // 24 hours

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {

        Customers c = customerRepository.findByUsername(username);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", c != null ? c.getRole() : "USER")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
package com.example.spring_security_jwt_without_oauth.services;

import com.example.spring_security_jwt_without_oauth.pojo.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.jsonwebtoken.Jwts;
/**
    Service for creating JWT token
 */
@Service
public class JWTUtilService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.sessionTime}")
    private long sessionTime;

    //Create jwt token (put into name and authorities)
    public String generateToken(UserDetailsImpl userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String commaSeparatedListOfAuthorities =
                userDetails
                        .getAuthorities()
                        .stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(","));
        //Add claim authorities to ou jwt token
        claims.put("authorities", commaSeparatedListOfAuthorities);
        String token = createToken(claims, userDetails.getUsername());
        return token;
    }

    //extracting username from token (inside token validation)
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //fetching authorities (inside token validation)
    public String extractAuthorities(String token) {
        Function<Claims, String> claimsListFunction = claims -> {
            return (String)claims.get("authorities");
        };
        return extractClaim(token, claimsListFunction);
    }

    private  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private String createToken(Map<String, Object> claims, String subject) {
        String token = Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(expireTimeFromNow())
                .signWith(SignatureAlgorithm.HS256,
                        SECRET_KEY).compact();
        return token;
    }

    private Date expireTimeFromNow() {
        Date date = new Date(System.currentTimeMillis() + sessionTime);
        return date;
    }

}

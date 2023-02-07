package com.example.spring_secutiry_standart_jwt_db_without_outh.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private JwtDecoder jwtDecoder;

    private AuthenticationManager authenticationManager;

    public JWTFilter(JwtDecoder jwtDecoder, AuthenticationManager authenticationManager) {
        this.jwtDecoder = jwtDecoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException
    {
        //Get header Authorization from request
        final String authorizationHeader = request
                .getHeader("Authorization");
        String username = null;
        String jwt = null;
        Jwt decodedJwt = null;
        //Cut prefix bearer if it not null and start with Bearer
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            //if the signature does not match the calculated one, then SignatureException
            //if the signature is incorrect (not parsed), then MalformedJwtException
            //if the signature has expired, then ExpiredJwtException
            decodedJwt = jwtDecoder.decode(jwt);
            username = decodedJwt.getClaim("name");
            System.out.println(username);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            String commaSeparatedListOfAuthorities = decodedJwt.getClaimAsString("authorities");
            System.out.println(commaSeparatedListOfAuthorities);
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(commaSeparatedListOfAuthorities);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            username, null, authorities);
            System.out.println(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}

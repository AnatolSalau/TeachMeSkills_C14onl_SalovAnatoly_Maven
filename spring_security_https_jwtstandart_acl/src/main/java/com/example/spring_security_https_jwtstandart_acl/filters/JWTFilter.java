package com.example.spring_security_https_jwtstandart_acl.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * JWTFilter for check JWT token
 * if JWT token is valid JwtAuthenticationToken will add in SecurityContext
 * in authentication
 */
@Component
public class JWTFilter extends OncePerRequestFilter {
      private JwtDecoder jwtDecoder;
      public JWTFilter(JwtDecoder jwtDecoder) {
            this. jwtDecoder = jwtDecoder;
      }
      @Override
      protected void doFilterInternal(HttpServletRequest request,
                                      HttpServletResponse response,
                                      FilterChain filterChain)
            throws ServletException, IOException
      {
            //Get header Authorization from request
            final String authorizationHeader = request
                  .getHeader("Authorization") ;
            String username = null;
            String jwt = null;
            Jwt decodedJwt = null;
            //Get SecurityContext
            SecurityContext context = SecurityContextHolder. getContext() ;
            //Cut prefix bearer if it not null and start with Bearer
            if (authorizationHeader != null &&
                  authorizationHeader.startsWith("Bearer ")) {
                  jwt = authorizationHeader.substring(7) ;
                  //if the signature does not match the calculated one, then SignatureException
                  //if the signature is incorrect (not parsed), then MalformedJwtException
                  //if the signature has expired, then ExpiredJwtException
                  decodedJwt = jwtDecoder.decode(jwt) ;
                  username = decodedJwt.getClaim("name") ;
            }
            //Set Authentication in context
            if (username != null &&
                  SecurityContextHolder. getContext().getAuthentication() == null
            ) {
                  String commaSeparatedListOfAuthorities =
                        decodedJwt.getClaimAsString("authorities") ;
                  System. out.println(commaSeparatedListOfAuthorities) ;
                  List<GrantedAuthority> authorities =
                        AuthorityUtils. commaSeparatedStringToAuthorityList(commaSeparatedListOfAuthorities) ;
                  //Create JwtAuthenticationToken
                  JwtAuthenticationToken jwtAuthenticationToken = new
                        JwtAuthenticationToken(
                        decodedJwt, authorities, username) ;
                  //Add authorities
                  context.setAuthentication(jwtAuthenticationToken) ;
                  filterChain.doFilter(request, response) ;
            }
            else {
                  System. out.println(context) ;
                  filterChain.doFilter(request, response);
            }
      }
}

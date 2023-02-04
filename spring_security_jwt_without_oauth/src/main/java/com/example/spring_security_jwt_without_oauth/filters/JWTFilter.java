package com.example.spring_security_jwt_without_oauth.filters;

import com.example.spring_security_jwt_without_oauth.services.JWTUtilService;
import com.example.spring_security_jwt_without_oauth.services.UserDetailslServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Filter for check exist or not JWT token
 * With each request, we take a JWT token from the Authorization header (it starts with the “Bearer” prefix).
 * We extract the username (claim subject) and the list of authorities (claim authorities) from it.
 * We wrote both brands into the token when it was generated in the controller.
 * At the same time, when retrieving claims, the validity of the token is checked.
 * To do this, you do not need to make any requests to the database: the token itself and jwt.secret (written in application.yml)
 * are enough. Based on this secret, the token was generated, and based on it, it is then checked each time using a hash function
 * (this is done by the jjwt library).
 * If everything is OK, then having the username and the list of authorities (extracted in step 2),
 * we create an Authentication object (more precisely, its subclass UsernamePasswordAuthenticationToken).
 * And set the Authentication object to the SecurityContext. So it is necessary for Spring Security.
 * If not everything is OK with the token, then an exception was thrown in paragraphs 2-3, and the filter will not let the request to the controller to the protected / url pass.
 */
@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtilService jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        //Get header Authorization from request
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        //Cut prefix bearer if it not null and start with Bearer
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            //if the signature does not match the calculated one, then SignatureException
            //if the signature is incorrect (not parsed), then MalformedJwtException
            //if the signature has expired, then ExpiredJwtException
            username = jwtUtil.extractUsername(jwt);
        }
            /*
            * If everything is OK, then having the username and the list of authorities (extracted in step 2),
            * we create an Authentication object (more precisely, its subclass UsernamePasswordAuthenticationToken).
            * And set the Authentication object to the SecurityContext. So it is necessary for Spring Security.
            */
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            String commaSeparatedListOfAuthorities = jwtUtil.extractAuthorities(jwt);
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(commaSeparatedListOfAuthorities);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            username, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }
        chain.doFilter(request, response);
    }
}

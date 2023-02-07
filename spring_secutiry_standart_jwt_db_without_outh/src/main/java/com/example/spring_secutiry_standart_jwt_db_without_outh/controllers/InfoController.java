package com.example.spring_secutiry_standart_jwt_db_without_outh.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A controller for the hello resource.
 * / Header Authorization : Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoidXNlciIsImV4cCI6MTY3NTcwNTk3NCwiaWF0IjoxNjc1NjY5OTc0LCJzY29wZSI6ImFwcCJ9.d5Ud_WevmbdIdKqssTeHyxfIpiOmuKtHa3PpYbifEB7fz7_DcHBx65KgcuWxyVx38BBI39cY-mSYa-dKZnRYWLqzG_CK4CWHap4ClDMyBpjP_O7Q0uHqRjkdCuC4cZQE2GZqwJuo5V4mmnREdrSGMXcHPizaRfERj-DZJEaNA1Y1Mcm6cJCHXHk7kzQI0LnBgg0w02ZJa68VJ_O3lJMulKPqrFZkKFjelBuZ0NgEkJLp5T3rBkWJGBsCI3Itvcubo0J5qTtsj1659V8TIKZkVcdFAfkWkXXQXF1G2raY9kXsQ9bkqSjnEHQLugFL7StTelmZHxpr8nb0Kc9TptKLvA
 */
@RestController
public class InfoController {

    @GetMapping("/info")
    public Map<String, Object> getPrincipalInfo(Authentication authentication, JwtAuthenticationToken principal) {

/*        List<String> authoritiesJWT = principal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());*/

/*        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> authoritiesList = authorities.stream()
                .map(element -> new String())
                .collect(Collectors.toList());*/

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication1 = context.getAuthentication();
        String contextName = authentication1.getName();
        Collection<? extends GrantedAuthority> authorities1 = authentication1.getAuthorities();
/*        List<String> authorities1List = authorities.stream()
                .map(element -> new String())
                .collect(Collectors.toList());*/

        Map<String, Object> info = new HashMap<>();
/*        info.put(" JWT name", principal.getName());*/
/*        info.put("JWT authorities", authoritiesJWT);
        info.put("JWT tokenAttributes", principal.getTokenAttributes());*/
        info.put("Authentication name", authentication1.getName());
/*        info.put("Authentication authoritiesList ", authoritiesList);*/
        info.put("contextName ", contextName);
        info.put("context authorities ", authorities1);
/*        info.put("ContextHolder authoritiesList ", authorities1List);*/
        return info;
    }
}
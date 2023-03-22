package com.example.spring_security_https_jwtstandart_acl.handlers;


import com.example.spring_security_https_jwtstandart_acl.dto.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationExceptionHandler implements AuthenticationEntryPoint {
      ObjectMapper objectMapper = new ObjectMapper();

      public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws  IOException {
            ErrorDTO errorDTO = new ErrorDTO(HttpServletResponse.SC_UNAUTHORIZED,
                  authenticationException.getMessage()) ;
            String string =
                  objectMapper.writeValueAsString(errorDTO) ;
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json") ;
            response.setCharacterEncoding("UTF-8") ;
            response.getWriter().write(string) ;
      }
}

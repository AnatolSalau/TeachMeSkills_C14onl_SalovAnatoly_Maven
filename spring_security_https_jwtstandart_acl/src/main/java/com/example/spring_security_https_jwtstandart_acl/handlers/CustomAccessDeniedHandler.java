package com.example.spring_security_https_jwtstandart_acl.handlers;

import com.example.spring_security_https_jwtstandart_acl.dto.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
      ObjectMapper objectMapper = new ObjectMapper();

      @Override
      public void handle(HttpServletRequest request,
                         HttpServletResponse response,
                         AccessDeniedException accessDeniedException) throws IOException, ServletException {
            String requestURI =
                  request.getRequestURI() ;
            ErrorDTO errorDTO = new ErrorDTO(500,
                  "CustomAccessDeniedHandler : requestURI : " +
                        requestURI) ;
            String string =
                  objectMapper.writeValueAsString(errorDTO) ;
            response.setStatus(500) ;
            response.setContentType("application/json") ;
            response.setCharacterEncoding("UTF-8") ;
            response.getWriter().write(string) ;

      }
}

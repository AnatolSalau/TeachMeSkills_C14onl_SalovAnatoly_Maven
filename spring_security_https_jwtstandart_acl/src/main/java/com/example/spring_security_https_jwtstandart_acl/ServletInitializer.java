package com.example.spring_security_https_jwtstandart_acl;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

      @Override
      protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(SpringSecurityHttpsJwtstandartAclApplication.class);
      }

}

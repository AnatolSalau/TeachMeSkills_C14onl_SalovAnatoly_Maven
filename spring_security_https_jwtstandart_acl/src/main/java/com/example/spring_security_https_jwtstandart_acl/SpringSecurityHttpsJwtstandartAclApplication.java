package com.example.spring_security_https_jwtstandart_acl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.example.spring_security_https_jwtstandart_acl",
      "com.example.spring_security_https_jwtstandart_acl.repositories"
})
@EntityScan("com.example.spring_security_https_jwtstandart_acl.entities")
public class SpringSecurityHttpsJwtstandartAclApplication {

      public static void main(String[] args) {
            SpringApplication.run(SpringSecurityHttpsJwtstandartAclApplication.class, args);
      }
}

package by.salov.lesson45_spring_security_roles.services;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class SecuredMethodService {
    /*Mark method as secured,*/
    @Secured("ROLE_USER")
    public void print(String str) {
        System.out.println(str);
    }
}

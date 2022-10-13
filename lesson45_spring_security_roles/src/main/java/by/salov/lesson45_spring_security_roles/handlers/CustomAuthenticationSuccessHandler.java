package by.salov.lesson45_spring_security_roles.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        /*Get ROLE collections*/
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println("authorities :" +  authorities);
        /*Convert roles collection on string array*/
        String[] rolesArray = authorities.stream()
                .map(Object::toString)
                .toArray(String[]::new);
        Arrays.stream(rolesArray).forEach( role ->{
                        try {
                            switch (role) {
                                case"USER" -> response.sendRedirect("/user");
                                case"DOCTOR" -> response.sendRedirect("/doctor");
                                case"ADMIN" -> response.sendRedirect("/admin");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
        );


    }
}

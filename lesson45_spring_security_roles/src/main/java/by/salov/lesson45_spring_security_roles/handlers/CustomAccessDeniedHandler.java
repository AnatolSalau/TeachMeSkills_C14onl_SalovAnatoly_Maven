package by.salov.lesson45_spring_security_roles.handlers;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) throws IOException {
        System.out.println("____________________CustomAccessDeniedHandler____________________");
        request.getSession().setAttribute("accessdenied", "true");
        response.sendRedirect("/accessdenied");
    }
}
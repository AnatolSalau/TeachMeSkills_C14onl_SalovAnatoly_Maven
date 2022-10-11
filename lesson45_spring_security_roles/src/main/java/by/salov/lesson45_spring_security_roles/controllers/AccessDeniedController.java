package by.salov.lesson45_spring_security_roles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class AccessDeniedController {
    @GetMapping("accessdenied")
    public String getAccessDenied(HttpServletRequest request) {
        String accessdenied = (String)request.getSession().getAttribute("accessdenied");
        System.out.println(accessdenied);
        if (accessdenied == null){
            return "login.html";
        }
        return "accessDenied.html";
    }
}

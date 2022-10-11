package by.salov.lesson45_spring_security_roles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AccessDeniedController {
    @GetMapping("accessdenied")
    public String getAccessDenied() {
        return "accessDenied.html";
    }
}

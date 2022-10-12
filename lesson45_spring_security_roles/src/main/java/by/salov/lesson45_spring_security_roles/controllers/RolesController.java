package by.salov.lesson45_spring_security_roles.controllers;

import by.salov.lesson45_spring_security_roles.services.SecuredMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;

@Controller
@RequestMapping(path = "/")
public class RolesController {

    @Autowired
    SecuredMethodService securedMethodService;

    @GetMapping(path = "/user")
    public String getUserTemplate() {
        /*Method mark as secured inside SecuredMethodService */
        securedMethodService.print("Hello from secured method");
        return "user.html";
    }

    @GetMapping(path = "/admin")
    public String getAdminTemplate() {
        return "admin.html";
    }

    @GetMapping(path = "/doctor")
    public String getDoctorTemplate() {
        return "doctor.html";
    }


    @Secured("ROLE_ADMIN")
    @GetMapping(path = "/test")
    public ModelAndView getTestTemplate(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("test.html");
        /*Get ROLE collections*/
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println("authorities :" +  authorities);
        /*Convert roles collection on string array*/
        String[] rolesArray = authorities.stream()
                .map(role -> role.toString())
                .toArray(String[]::new);
        Arrays.stream(rolesArray).forEach(System.out::println);
        modelAndView.addObject("roles",rolesArray);
        return modelAndView;
    }
}

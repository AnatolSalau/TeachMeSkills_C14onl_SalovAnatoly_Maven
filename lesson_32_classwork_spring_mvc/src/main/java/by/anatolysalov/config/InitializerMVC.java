package by.anatolysalov.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class InitializerMVC implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.scan("by.anatolysalov");
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", new DispatcherServlet(applicationContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}

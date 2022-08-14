package by.salov.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//Load our spring app
@Configuration
@EnableWebMvc
public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan("by.salov");
        ServletRegistration.Dynamic registration = servletContext.addServlet(
                "dispatcher", new DispatcherServlet(context)
        );
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}

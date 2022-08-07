package by.salov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class ContextConfig implements WebMvcConfigurer {

    //configureDefaultServletHandling onfigurer.enable(); - turn on
    //if you override configureDefaultServletHandling() and enabling it
    // you are essentially asking default servlet (mapped to "/") to serve the resources.
    // JAVA docs:
    //This allows for mapping the DispatcherServlet to "/" (thus overriding the mapping of the container’s default Servlet),
    // while still allowing static resource requests to be handled by the container’s default Servlet.
    //https://stackoverflow.com/questions/29396281/what-does-configuredefaultservlethandling-means
    //in xml configuration ->
    //<mvc:default-servlet-handler />
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //We must don't set suffix in viewResolver(), because if we do it, addViewControllers() below -> it doesn't work
    // -> we will have end of url -> resources/html/test.html.jsp
    @Bean
    ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        //resolver.setSuffix(".jsp");
        return resolver;
    }

    //I add static resource location in WEB-INF folder, because if we create resource folder just like: webapp/resources
    //users can get it by url, so I put it on webapp/WEB-INF/resources
    //in xml configuration ->
    //<mvc:resources mapping="/abc/**" location="/WEB-INF/resources/"/>
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
    }

    //First way how display html page in browser, just use ViewControllerRegistry
    //Display html page from resources (with css and js )
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/test").setViewName("resources/html/test.html");
        //("redirect:/resources/html/test.html");
    }
}

package by.salov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class ContextConfig implements WebMvcConfigurer {
    //<mvc:default-servlet-handler />
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    @Bean
    ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        //resolver.setSuffix(".jsp");
        return resolver;
    }
    //<mvc:resources mapping="/abc/**" location="/resources/" />
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/*.html").addResourceLocations("/WEB-INF/");
        registry.addResourceHandler("/*.css").addResourceLocations("/WEB-INF/");
    }

    //Display html page from resources
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/test").setViewName("resources/html/test.html");
        //("redirect:/resources/html/test.html");
    }
}

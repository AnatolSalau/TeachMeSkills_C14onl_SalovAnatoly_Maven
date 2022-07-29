package by.anatoly.config;

import by.anatoly.interceptors.LogHandleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Configuration for interceptors registration
//@EnableWebMvc - annotation for mandatory execution of methods
//(обязательного выполнения методов)
@Configuration
@EnableWebMvc
public class ConfigurationInterceptors implements WebMvcConfigurer {
    @Override

    public void addInterceptors(InterceptorRegistry registry) {
        //Register LogHandleInterceptor
        registry.addInterceptor(new LogHandleInterceptor());
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

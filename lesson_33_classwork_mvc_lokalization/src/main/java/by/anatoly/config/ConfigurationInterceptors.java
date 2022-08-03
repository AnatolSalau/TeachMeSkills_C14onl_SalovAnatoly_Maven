package by.anatoly.config;

import by.anatoly.interceptors.LogHandleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

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

        //Register LocaleChangeInterceptor for localization our app
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        //Change default name "locale" to "lang"
        //Our interceptor will be check parameter with name lang
        //If this parameter will be changed - this means - locale should also be changed
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);

        //Add registy of interceptors to WebMvcConfigurer
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

package by.anatoly.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;

@Configuration
@EnableWebMvc
public class ConfigurationMVC implements WebMvcConfigurer {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver(); //PATH to JSP pages
        viewResolver.setPrefix("/WEB-INF/pages/"); viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(100000);
        return multipartResolver;
    }
    //Register resolver for work with locale
    //LocaleResolver can change locale (has )
    @Bean
    public LocaleResolver localeResolver() {
        //LocaleResolver has several implementations:
        // AcceptHeaderLocaleResolver - save locale in header
        // CookieLocaleResolver - save locale in cookie
        // SessionLocaleResolver - save locale in cookie session (JSESSIONID)
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        //Set default language (english)(locale) - first connection
        resolver.setDefaultLocale(new Locale("en"));
        return resolver;
    }

    //Property file for messages
    @Bean
    MessageSource messageSource() {
        //Message source can get messages from database or property file
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        //set encoding in property file
        source.setDefaultEncoding("UTF-8");
        //Path to bundles
        source.setBasename("i18n/label");
        return source;
    }
}

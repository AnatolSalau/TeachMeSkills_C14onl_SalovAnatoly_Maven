package by.salov.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*Old spring security settings by WebSecurityConfigurerAdapter*/
@Configuration
@EnableWebSecurity
public class SecutityConfig extends WebSecurityConfigurerAdapter {
    /*configure - object that configure spring security*/
/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        *//*we may authorize(check) all requests by authorizeHttpRequests*//*
        http.authorizeHttpRequests()
                *//*allow all connection to the users pages*//*
*//*                .antMatchers("/user").permitAll()*//*

                *//*allow access only for method GET*//*
                .mvcMatchers(HttpMethod.GET,"/user/**").permitAll()

                *//*allow access all after users*//*
*//*                .antMatchers("/users/**").permitAll()*//*

                *//*disable all connection to the admin pages*//*
                *//*.antMatchers("/admin").denyAll()*//*

                *//*allow access only by authorized*//*
                .antMatchers("/admin").authenticated()
                .and()
                .formLogin();
    }*/
}

package com.example.spring_security_jwt_without_oauth.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration without DB in memory
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class InMemorySecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //Create InMemoryUserDetailService which return users from memory
    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        InMemoryUserDetailsManager inMemoryUserDetailsManager =
                new InMemoryUserDetailsManager();
        //Create user
        inMemoryUserDetailsManager
                .createUser(
                        User.withUsername("user")
                                .password(bCryptPasswordEncoder.encode("user"))
                                .roles("USER")
                                .build()
                );
        //Create admin
        inMemoryUserDetailsManager
                .createUser(
                        User.withUsername("admin")
                                .password(bCryptPasswordEncoder.encode("admin"))
                                .roles("USER", "ADMIN")
                                .build()
                );
        return inMemoryUserDetailsManager;

    }

    //Create AuthenticationManager which compare login and passwords
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailsService userDetailsService)
        throws Exception {
        AuthenticationManager authenticationManager = httpSecurity
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
        return authenticationManager;
    }

    //Chain of configuration, HTTP settings
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.GET, "/", "/permitall/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/", "/permitall/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/admins/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/admins/").hasRole("ADMIN")
                        .anyRequest().denyAll())
                .httpBasic();
        return  httpSecurity.build();
    }
    @Value("${spring.websecurity.debug:false}")
    boolean webSecurityDebug;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(webSecurityDebug)
                .ignoring()
                .requestMatchers("/ignoring/**", "/favicon.ico");
    }
}

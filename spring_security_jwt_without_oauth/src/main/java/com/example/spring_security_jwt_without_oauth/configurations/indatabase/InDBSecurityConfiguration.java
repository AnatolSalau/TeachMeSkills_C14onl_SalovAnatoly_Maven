package com.example.spring_security_jwt_without_oauth.configurations.indatabase;

import com.example.spring_security_jwt_without_oauth.handlers.CustomAccessDeniedHandler;
import com.example.spring_security_jwt_without_oauth.services.UserDetailslServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Security configuration without DB in memory
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class InDBSecurityConfiguration {

    @Autowired
    private UserDetailslServiceImpl userDetailslServiceImpl;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //Create InMemoryUserDetailService which return users from memory

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailslServiceImpl;
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
                .authorizeHttpRequests(requests -> {
                    try {
                        requests
                                .requestMatchers(HttpMethod.GET, "/", "/permitall/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/", "/permitall/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/users/").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/users/").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/admins/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/admins/").hasRole("ADMIN")
                                .anyRequest().denyAll()
                                .and()
                                .exceptionHandling()
                                .accessDeniedHandler(customAccessDeniedHandler);
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                })
                .httpBasic();
        return  httpSecurity.build();
    }
    @Value("${spring.websecurity.debug:false}")
    boolean webSecurityDebug;

    //Add path to ignore authentication
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(webSecurityDebug)
                .ignoring()
                .requestMatchers("/ignoring/**", "/favicon.ico");
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}

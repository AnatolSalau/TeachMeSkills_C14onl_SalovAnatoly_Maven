package com.example.spring_security_jwt_without_oauth.configurations.indatabase;

import com.example.spring_security_jwt_without_oauth.filters.JWTFilter;
import com.example.spring_security_jwt_without_oauth.handlers.CustomAccessDeniedHandler;
import com.example.spring_security_jwt_without_oauth.services.UserDetailslServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring 3.0 Security configuration with DB with JwtFilter (JWT token)
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class InDBSecurityConfiguration {
    @Value("${spring.websecurity.debug}")
    boolean webSecurityDebug;

    @Autowired
    private JWTFilter jwtFilter;

    @Autowired
    private UserDetailslServiceImpl userDetailslServiceImpl;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailslServiceImpl;
    }

    //Create AuthenticationManager
    //@Bean
    /*
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailslServiceImpl userDetailService)
        throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }
    */

    //Create AuthenticationProvider which compare login and passwords
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;
    }

    //Chain of configuration, HTTP settings
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                    .disable()
                .authorizeHttpRequests()
                    .requestMatchers(   "/",
                                                "/permitall/**",
                                                "/authenticate/"
                    )
                    .permitAll()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                    .requestMatchers(   "/api/v1/users/",
                                                "/api/v1/admins/"
                    )
                    .authenticated()
                .anyRequest().denyAll()
                .and()
                .exceptionHandling()
                    .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .addFilterBefore(jwtFilter,
                        UsernamePasswordAuthenticationFilter.class);
                //.formLogin();
        return  httpSecurity.build();
    }
//  Security debugging is enabled.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(webSecurityDebug);
    }
}

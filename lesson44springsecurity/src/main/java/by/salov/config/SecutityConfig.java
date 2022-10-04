package by.salov.config;

import by.salov.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/*Old spring security settings by WebSecurityConfigurerAdapter*/
@Configuration
@EnableWebSecurity
public class SecutityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /*We may  use JdbcUserDetailsManager if we work with
    * spring standart table structure */
/*    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;*/

    /*configure - object that configure spring security*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*we may authorize(check) all requests by authorizeHttpRequests*/
        http.authorizeHttpRequests()
                /*allow all connection to the users pages*/
/*                .antMatchers("/user").permitAll()*/

                /*allow access only for method GET*/
                .mvcMatchers(HttpMethod.GET,"/user/**").permitAll()

                /*allow access all after users*/
/*                .antMatchers("/users/**").permitAll()*/

                /*disable all connection to the admin pages*/
                /*.antMatchers("/admin").denyAll()*/

                /*allow access only by authorized*/
                .antMatchers("/admin").authenticated()
                .and()
                .formLogin();
    }
    /*Create users in memory, we can use their in our authentication*/
    /*Also we must add PasswordEncoder because we can't store unencrypted password
    * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/*        NoOpPasswordEncoder.getInstance() - doesn't do anything, just compare string
        auth.inMemoryAuthentication()
                .withUser(User.builder().username("loginuser1").password("password").authorities("read"))
                .withUser(User.builder().username("loginadmin1").password("password").authorities("write"))
                .passwordEncoder(NoOpPasswordEncoder.getInstance());*/
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
        /*Use BCryptPasswordEncoder() */
/*                .passwordEncoder(new BCryptPasswordEncoder());*/

    }
}

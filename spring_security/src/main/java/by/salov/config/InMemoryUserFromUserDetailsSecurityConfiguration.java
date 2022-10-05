package by.salov.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * Authentication in memory by default User from userdetails
 */
@EnableWebSecurity
public class InMemoryUserFromUserDetailsSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
        * Add in authentication two users
         */
     auth.inMemoryAuthentication()
             .withUser(User.builder().username("loginuser1").password("password").authorities("read"))
             .withUser(User.builder().username("loginadmin1").password("password").authorities("write"))
                /*
                * Add password encoder
                 */
             .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
    /*
    * Configure access to our url
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/user").permitAll()
                .antMatchers("/admin").authenticated()
                .and()
                .formLogin();
    }
}

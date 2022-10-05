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
        * Add  authentication for two users, and mark their roles
         */
     auth.inMemoryAuthentication()
             .withUser(User.builder().username("user").password("user").authorities("ROLE_USER"))
             .withUser(User.builder().username("admin").password("admin").authorities("ROLE_ADMIN"))
                /*
                * Add password encoder
                 */
             .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
    /*
    * Configure access to our url, and configure authorization (adding roles)
    *   /**         - means any urls
    *   /admin/**   - means urls that start fom /admin
    * If we start enumeration urls with common case like /**,
    * then any enumeration stop on it (because any url, including /admin) correspond -> /** ,
    * which means -> all urls will have access. Just because You need to start with narrowly covering templates.
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }
}
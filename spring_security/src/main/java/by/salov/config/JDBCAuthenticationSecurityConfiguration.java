package by.salov.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

/**
 * You must create two query from db, the names of this are not important:
 *      username
 *      password
 *      enabled (user enable)
 *
 *      username
 *      authority
 */

@EnableWebSecurity
public class JDBCAuthenticationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/**").permitAll()
                .and()
                /*customization login page*/
                .formLogin().permitAll()
                .loginPage("/login")
                .loginProcessingUrl("/perform-login")
                .usernameParameter("user")
                .passwordParameter("pass")
                .defaultSuccessUrl("/")
                /*add remember cookie */
                .and().rememberMe()
                .and()
                /*customization logout page*/
                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                /*
                *      username
                *      password
                *      enabled (user enable)
                */
                .usersByUsernameQuery(
                        "select login, password, 'true' from my_user " +
                                "where login=?")
                /*
                *      username
                *      authority
                */
                .authoritiesByUsernameQuery(
                        "select login, role from my_user " +
                                "where login=?")
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}

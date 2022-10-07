package by.salov.config;

import by.salov.components.MyUserAuthencationProviderImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * Authentication with custom AuthenticationProvider (create object Authentication)
 */

/*@EnableWebSecurity*/
public class CustomAuthenticationProviderSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserAuthencationProviderImp myUserAuthencationProviderImp;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //и добавляем его сюда
        auth
                .authenticationProvider(myUserAuthencationProviderImp);
    }

    /*
     * Configure access to our url, and configure authorization (adding roles)
     *   /**         - means any urls
     *   /admin/**   - means urls that start fom /admin
     * If we start enumeration urls with common case like /**,
     * then any enumeration stop on it (because any url, including /admin) correspond -> /** ,
     * which means -> all urls will have access. Just because You need to start with narrowly covering templates.
     * Customization login page
     */
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
                .and()
                /*customization logout page*/
                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }
}

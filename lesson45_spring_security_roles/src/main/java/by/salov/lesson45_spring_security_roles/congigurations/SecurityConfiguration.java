package by.salov.lesson45_spring_security_roles.congigurations;

import by.salov.lesson45_spring_security_roles.filters.SecurityLogFilter;
import by.salov.lesson45_spring_security_roles.handlers.CustomAuthenticationSuccessHandler;
import by.salov.lesson45_spring_security_roles.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailServiceImpl)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/accessdenied").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/doctor/**").hasAnyRole("DOCTOR", "ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER","DOCTOR", "ADMIN")
                .antMatchers("/**").permitAll()

                .and()
                /*customization login page*/
                .formLogin()
                .permitAll()
                .loginPage("/login")
                .loginProcessingUrl("/perform-login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .successHandler(customAuthenticationSuccessHandler)
                .and()
                .addFilterBefore(new SecurityLogFilter(), LogoutFilter.class)
/*                .addFilterBefore(new SecurityLogFilter(), LogoutFilter.class)*/
                /*Add Exeption handlers*/
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                /*add simple hash based token*/
                .rememberMe()
                .and()
                /*customization logout page*/
                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }
}

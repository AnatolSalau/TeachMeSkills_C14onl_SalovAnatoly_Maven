package by.salov.lesson45_spring_security_roles.congigurations;

import by.salov.lesson45_spring_security_roles.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailService)
                /*Без кодирования все работает*/
/*                .passwordEncoder(NoOpPasswordEncoder. getInstance());*/

                /* C BCryptPasswordEncoder() не работает, но при это я реализовал
                * AuthenticationProvider - где в ручную прописал сравнение через BCryptPasswordEncoder
                * там все работает*/

                /* не работает -> */
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/doctor/**").authenticated()
                .antMatchers("/admin/**").authenticated()
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

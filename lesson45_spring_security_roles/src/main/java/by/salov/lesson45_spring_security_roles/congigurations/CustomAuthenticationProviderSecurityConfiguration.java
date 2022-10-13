package by.salov.lesson45_spring_security_roles.congigurations;

import by.salov.lesson45_spring_security_roles.components.UserAuthencationProviderImp;
import by.salov.lesson45_spring_security_roles.filters.SecurityLogFilter;
import by.salov.lesson45_spring_security_roles.handlers.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * The prePostEnabled = true -> property enables Spring Security pre/post annotations.
 * The securedEnabled = true -> property determines if the @Secured annotation should be enabled.
 * The jsr250Enabled = true -> property allows us to use the @RoleAllowed annotation.
 */
@EnableWebSecurity (/*debug = true*/)
@EnableGlobalMethodSecurity (
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
        )
public class CustomAuthenticationProviderSecurityConfiguration  extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserAuthencationProviderImp userAuthencationProviderImp;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(userAuthencationProviderImp);
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
/*                .successHandler(customAuthenticationSuccessHandler)*/
                /*Add filter SecurityLogFilter*/
                .and()
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
                /*Setting CORS policy
                * Cross-Origin Resource Sharing -> forbids access from one site to another
                * CSRF - protection from attack one site to another by cookies
                * */
/*                .and()
                .csrf()
                .disable()
                .cors()
                .disable();*/
    }
}

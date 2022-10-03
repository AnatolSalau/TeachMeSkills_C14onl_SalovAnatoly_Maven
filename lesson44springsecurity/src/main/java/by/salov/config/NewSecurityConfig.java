package by.salov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*@Configuration
@EnableWebSecurity*/
public class NewSecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        /*For setting authentication we must return httpSecurity
        * use lambda function for setting authentication */
        return httpSecurity.authorizeHttpRequests(auth -> {
            auth
                    .antMatchers("/user").permitAll()
                    .antMatchers("/admin").authenticated();
        })
                .formLogin()
                .and()
                .build();
        /*.build() - create SecurityFilterChain object*/
    }
}

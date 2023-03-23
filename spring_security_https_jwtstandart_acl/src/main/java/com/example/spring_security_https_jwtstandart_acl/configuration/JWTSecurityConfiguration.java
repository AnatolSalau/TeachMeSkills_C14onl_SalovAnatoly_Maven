package com.example.spring_security_https_jwtstandart_acl.configuration;

import com.example.spring_security_https_jwtstandart_acl.filters.JWTFilter;
import com.example.spring_security_https_jwtstandart_acl.handlers.CustomAccessDeniedHandler;
import com.example.spring_security_https_jwtstandart_acl.handlers.CustomAuthenticationExceptionHandler;
import com.example.spring_security_https_jwtstandart_acl.services.UserDetailslServiceImpl;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class JWTSecurityConfiguration {

      @Autowired
      UserDetailslServiceImpl userDetailslService;

      @Autowired
      CustomAuthenticationExceptionHandler authenticationExceptionHandler;

      @Autowired
      CustomAccessDeniedHandler customAccessDeniedHandler;

      @Value("${websecurity.debug}")
      boolean webSecurityDebug;

      // RSAPublicKey
      @Value("${jwt.public.key}")
      RSAPublicKey key;
      // RSAPrivateKey
      @Value("${jwt.private.key}")
      RSAPrivateKey priv;

      // Security debugging is enabled.
      @Bean
      public WebSecurityCustomizer
      webSecurityCustomizer() {
            return (web) -> web.debug(webSecurityDebug);
      }

      // Filter for
      @Bean
      public JWTFilter jwtFilter(JwtDecoder jwtDecoder) {
            return new JWTFilter(jwtDecoder);
      }

      // Decoder for jwt
      @Bean
      JwtDecoder jwtDecoder() {
            return NimbusJwtDecoder.withPublicKey(this.key).build();
      }

      // Encoder for jwt
      @Bean
      JwtEncoder jwtEncoder() {
            JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
            JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new
                  JWKSet(jwk));
            return new NimbusJwtEncoder(jwks);
      }

      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                     JwtDecoder jwtDecoder) throws Exception {
            http
                  .authorizeHttpRequests()
                  .requestMatchers(
                        "/"
                  )
                  .permitAll()
                  .and()
                  .authorizeHttpRequests()
                  .requestMatchers(
                        "/api/v1/users",
                        "/api/v1/admins",
                        "/api/v1/document",
                        "/api/v1/document/**",
                        "/authenticate"
                  )
                  .authenticated()
                  .anyRequest().denyAll()
                  .and()
                  .csrf((csrf) -> csrf.ignoringRequestMatchers("/authenticate"))
                  .httpBasic(Customizer.withDefaults())
                  .sessionManagement(
                        (session) -> session.sessionCreationPolicy(
                                    SessionCreationPolicy.STATELESS)
                              .and()
                              .addFilterBefore(jwtFilter(jwtDecoder),
                                    UsernamePasswordAuthenticationFilter.class)
                  )
                  .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(authenticationExceptionHandler)
                        .accessDeniedHandler(customAccessDeniedHandler)
                  );

            return http.build();
      }

      //Encoder for encode passwords from DB
      @Bean
      public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
      }

      @Bean
      public AuthenticationProvider authenticationProvider(UserDetailslServiceImpl userDetailsService) {
            DaoAuthenticationProvider authenticationProvider = new
                  DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(userDetailsService);
            authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
            return authenticationProvider;
      }
}

package by.salov.lesson45_spring_security_roles.components;

import by.salov.lesson45_spring_security_roles.dao.UserRepository;
import by.salov.lesson45_spring_security_roles.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/*Компонент для рабочей конфигурации SpringSecurity через свою реализацию AuthenticationProvider */
@Component
public class UserAuthencationProviderImp implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        /*Get password and login from client*/
        String usernameClient = authentication.getName();
        System.out.println("usernameFromClient : " + usernameClient);
        String passwordClien = authentication.getCredentials().toString();
        System.out.println("passwordFromClient : " + passwordClien);

        /*Get user from DB*/
        Optional<User> myUserOptional =
                userRepository.findUserByLogin(usernameClient) ;
        User userDB = myUserOptional.orElseThrow(() ->new
                BadCredentialsException("Login not found" +
                myUserOptional.get().getLogin())) ;
        System.out.println("usernameFromDB : " + userDB.getLogin());
        System.out.println("passwordFromDB : " + userDB.getPassword());
        /*Equal raw password from db*/
/*        if (!passwordClien.equals(userDB.getPassword())) {
            throw new BadCredentialsException("Bad password") ;
        }*/
        /*Equal bcrypt password from db*/
        if (bCryptPasswordEncoder.matches(passwordClien,userDB.getPassword())) {
            throw new BadCredentialsException("Bad password");
        }

        /*Create String array roles*/
        String[] rolesArray = userDB.getRoleList().stream()
                .map(role -> role.getName())
                .toArray(String[]::new);
        /*Create UserDetails*/
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
            .username(userDB.getLogin())
            .password(userDB.getPassword())
            .roles(rolesArray)
            .build() ;
        /*Create and return token*/
    return new UsernamePasswordAuthenticationToken(
            userDetails, passwordClien, userDetails.getAuthorities());
}

    @Override
    public boolean supports(Class<?> authentication) {
        return
        authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}

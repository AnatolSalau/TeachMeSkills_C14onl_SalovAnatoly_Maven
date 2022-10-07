package by.salov.components;

import by.salov.dao.MyUserJpaRepository;
import by.salov.models.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Custom implementation AuthenticationProvider
 */
@Component
public class MyUserAuthencationProviderImp implements AuthenticationProvider {

    @Autowired
    MyUserJpaRepository myUserJpaRepository;

    /**
     * @param authentication
     * @return Authentication
     * @throws AuthenticationException
     * authentication.getName() - user name from client
     * authentication.getCredentials().toString() - password from client
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        /*user name from client*/
        String userLogin = authentication.getName();
        /*password from client*/
        String userPassword = authentication.getCredentials().toString();
        Optional<MyUser> myUserOptional = myUserJpaRepository.findMyUserByLogin(userLogin);
        MyUser myUser = myUserOptional.orElseThrow(() ->new BadCredentialsException("Login not found" + myUserOptional.get().getLogin()));

        if (!userPassword.equals(myUser.getPassword())) {
            throw new BadCredentialsException("Bad password");
        }
        UserDetails userDetails = User.builder()
                .username(myUser.getLogin())
                .password(myUser.getPassword())
                .roles(myUser.getRole())
                .build();
        return new UsernamePasswordAuthenticationToken(
                userDetails, userPassword, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

package com.example.spring_security_jwt_without_oauth.services;

import com.example.spring_security_jwt_without_oauth.entities.User;
import com.example.spring_security_jwt_without_oauth.exceptions.UserRuntimeException;
import com.example.spring_security_jwt_without_oauth.pojo.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailslServiceImpl implements UserDetailsService {

    @Autowired
    private UserCRUDService userCRUDService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByLogin = userCRUDService.findUserByLogin(username);
        UserDetailsImpl userDetails = new UserDetailsImpl(userByLogin);
        if (userDetails == null) {
            throw new UserRuntimeException(
                    500,
                    ("Cant create userDetails in" + this.getClass().getSimpleName())
            );
        }
        return userDetails;
    }
}

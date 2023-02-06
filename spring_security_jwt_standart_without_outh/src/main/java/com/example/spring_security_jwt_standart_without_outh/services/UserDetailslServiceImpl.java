package com.example.spring_security_jwt_standart_without_outh.services;

import com.example.spring_security_jwt_standart_without_outh.entities.User;
import com.example.spring_security_jwt_standart_without_outh.exceptions.UserRuntimeException;
import com.example.spring_security_jwt_standart_without_outh.pojo.UserDetailsImpl;
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
        System.out.println("---------------------User details---------------------------");
        System.out.println(userDetails);
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());
        return userDetails;
    }
}

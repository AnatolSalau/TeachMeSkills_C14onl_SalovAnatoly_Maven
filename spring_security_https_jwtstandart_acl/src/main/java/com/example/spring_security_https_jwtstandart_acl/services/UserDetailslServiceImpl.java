package com.example.spring_security_https_jwtstandart_acl.services;

import com.example.spring_security_https_jwtstandart_acl.entities.User;
import com.example.spring_security_https_jwtstandart_acl.exceptions.ServerRuntimeException;
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
            User userByLogin = userCRUDService.findUserByLogin(username) ;
            UserDetailsImpl userDetails = new UserDetailsImpl(userByLogin) ;
            if (userDetails == null) {
                  throw new ServerRuntimeException(
                        500,
                        ("Cant create userDetails in" +
                              this.getClass().getSimpleName())
                  ) ;
            }
            return userDetails;
      }
}

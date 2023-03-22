package com.example.spring_security_https_jwtstandart_acl.services;

import com.example.spring_security_https_jwtstandart_acl.entities.User;
import com.example.spring_security_https_jwtstandart_acl.exceptions.ServerRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.spring_security_https_jwtstandart_acl.repositories.UserJpaRepository;

import java.util.List;

@Service
public class UserCRUDService {
      @Autowired
      private UserJpaRepository userJpaRepository;
      public List<User> findAllUsers() {
            List<User> allUsers = userJpaRepository.findAll() ;
            if(allUsers.isEmpty() || allUsers == null) {
                  throw new ServerRuntimeException(
                        500,
                        "users table is empty"
                  ) ;
            }
            return allUsers;
      }
      public User findUserByLogin(String login) {
            User userByLogin = userJpaRepository.findUserByLogin(login) ;
            if (userByLogin == null) {
                  throw new ServerRuntimeException(
                        500,
                        ("Cant find user with login : " + login)
                  ) ;
            }
            return userByLogin;
      }
      public User saveUser(User user) {
            System. out.println(user) ;
            User savedUser = userJpaRepository.save(user) ;
            if (savedUser == null) {
                  throw new ServerRuntimeException(
                        500,
                        "Saved user is null"
                  ) ;
            }
            return savedUser;
      }
}


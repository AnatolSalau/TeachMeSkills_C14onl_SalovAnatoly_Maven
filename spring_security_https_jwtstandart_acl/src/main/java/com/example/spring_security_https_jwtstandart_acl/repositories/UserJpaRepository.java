package com.example.spring_security_https_jwtstandart_acl.repositories;

import com.example.spring_security_https_jwtstandart_acl.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
      List<User> findAll() ;
      User findUserByLogin(String login) ;
      User save(User user) ;
}
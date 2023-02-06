package com.example.spring_security_jwt_standart_without_outh.repository;

import com.example.spring_security_jwt_standart_without_outh.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<User,Long> {
    List<User> findAll();

    User findUserByLogin(String login);

    User save(User user);
}

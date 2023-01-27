package com.example.spring_security_jwt_without_oauth.repository;

import com.example.spring_security_jwt_without_oauth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<User,Long> {
    List<User> findAll();

    User findUserByLogin(String login);

    User save(User user);
}

package com.example.spring_secutiry_standart_jwt_db_without_outh.repository;


import com.example.spring_secutiry_standart_jwt_db_without_outh.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface UserJpaRepository extends JpaRepository<User,Long> {
    List<User> findAll();

    User findUserByLogin(String login);

    User save(User user);
}

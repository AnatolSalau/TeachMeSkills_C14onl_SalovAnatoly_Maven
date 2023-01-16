package com.example.rest_microservice_userbackend.repositories;

import com.example.rest_microservice_userbackend.entities.User;
import com.example.rest_microservice_userbackend.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    UserProjection findUserById(UUID id);
    UserProjection findUserByLogin(String login);
    List<UserProjection> findAllProjectedBy();
    User  save(User user);
}

package com.example.rest_microservice_userbackend_feignlient.repositories;


import com.example.rest_microservice_userbackend_feignlient.entities.User;
import com.example.rest_microservice_userbackend_feignlient.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    UserProjection findUserById(UUID id);
    UserProjection findUserByLogin(String login);
    List<UserProjection> findAllProjectedBy();
    User  save(User user);
}

package com.example.rest_microservice_facade_feignclient.services;

import com.example.rest_microservice_facade_feignclient.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

/**
 *  OpenFeignClient to connect with User microservice
 */
@FeignClient(
        name = "user-feign",
        url = "http://127.0.0.1:8082/api/v2/user/"
)
public interface UserFeignClient {

    @GetMapping()
    List<UserDTO> getAllUsers();

    @GetMapping(path = "{userLogin}")
    UserDTO getUserByLogin(@PathVariable("userLogin")String userLogin);
    @GetMapping(path = "id/{id}")
    UserDTO getUserById(@PathVariable("id")UUID id);
    @PostMapping()
    UserDTO saveUser(@RequestBody UserDTO userDTO);
}

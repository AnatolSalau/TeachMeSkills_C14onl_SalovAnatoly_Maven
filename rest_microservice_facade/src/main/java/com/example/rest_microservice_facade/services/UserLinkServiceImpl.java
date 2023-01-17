package com.example.rest_microservice_facade.services;

import com.example.rest_microservice_facade.dto.ErrorDTO;
import com.example.rest_microservice_facade.dto.UserDTO;
import com.example.rest_microservice_facade.exception.UserRuntimeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Rest template for link with other microservice
 */
@Service
public class UserLinkServiceImpl implements UserLinkService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final String GET_ALL_USERS = "http://127.0.0.1:8082/api/v2/user";
    private final String GET_USER_BY_LOGIN = "http://127.0.0.1:8082/api/v2/user";
    private final String GET_USER_BY_ID = "http://127.0.0.1:8082/api/v2/user/id";
    private final String SAVE_USER = "http://127.0.0.1:8082/api/v2/user";

    @Override
    public List<UserDTO> getAllUsers() {
        ResponseEntity<UserDTO[]> responseEntityFromBackend = restTemplate
                .getForEntity(GET_ALL_USERS, UserDTO[].class);
        UserDTO[] userDTOArray = responseEntityFromBackend.getBody();
        //Error handle
        HttpStatusCode statusCode = responseEntityFromBackend.getStatusCode();
        if (statusCode.is5xxServerError()) {
            System.out.println(userDTOArray);
            System.out.println(userDTOArray);
            System.out.println();
        }
        return Arrays.asList(userDTOArray);
    }

    @Override
    public UserDTO getUserByLogin(String userLogin) {
        ResponseEntity<UserDTO> responseEntityFromBackend = restTemplate
                .getForEntity(GET_USER_BY_LOGIN + "/" + userLogin, UserDTO.class);
        UserDTO userDTO = responseEntityFromBackend.getBody();
        return userDTO;
    }


    @Override
    public UserDTO getUserById(UUID id) {
        return null;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {

        return null;
    }


}

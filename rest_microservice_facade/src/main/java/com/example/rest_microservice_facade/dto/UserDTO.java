package com.example.rest_microservice_facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@NoArgsConstructor
@Data

public class UserDTO {

    private UUID id;
    private String login;
    private String password;
    private String email;

    public UserDTO(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
}

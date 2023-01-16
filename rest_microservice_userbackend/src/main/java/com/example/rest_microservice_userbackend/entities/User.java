package com.example.rest_microservice_userbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class User {

    private UUID id;
    private String login;
    private String password;
    private String email;

}

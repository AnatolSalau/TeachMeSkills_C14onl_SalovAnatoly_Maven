package com.example.spring_rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;


import java.util.Date;

/**
 * HATEOAS : extends RepresentationModel<User>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User extends RepresentationModel<User> {
    private Long id;
    @NotNull
    private String login;
    @NotNull
    private String password;
    private Gender gender;
    private Date date;
}

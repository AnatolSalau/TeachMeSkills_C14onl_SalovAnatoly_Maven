package com.example.spring_rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Long id;
    @NotNull
    private String login;
    @NotNull
    private String password;
    private Gender gender;
    private Date date;
}

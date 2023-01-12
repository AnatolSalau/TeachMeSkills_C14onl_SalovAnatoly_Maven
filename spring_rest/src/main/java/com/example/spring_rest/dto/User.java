package com.example.spring_rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Long id;
    private String login;
    private String password;
    private Gender gender;
    private Date date;
}

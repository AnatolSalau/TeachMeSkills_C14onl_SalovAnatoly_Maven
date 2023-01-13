package com.example.spring_rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SearchDTO {
    private String username;
    private Gender gender;
    private Long minId;
    private Long maxId;
}

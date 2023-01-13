package com.example.spring_rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SearchDTO {
    //change name property in JSON
    @JsonProperty(value = "userName")
    private String login;
    private Gender gender;
    private Long minId;
    private Long maxId;
}

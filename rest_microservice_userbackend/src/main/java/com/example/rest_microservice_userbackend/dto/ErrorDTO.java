package com.example.rest_microservice_userbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
* Just object, which RestAdviceController will response to client
*/
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ErrorDTO {
    private int statusCode;
    private String messages;
}

package com.example.spring_rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Just object, which RestAdviceController will response to client
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ErrorDTO {
    private String message;
}

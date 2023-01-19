package com.example.spring_security_jwt_without_oauth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Just object, which RestAdviceController will response to
 client
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private int statusCode;
    private String messages;
}
package com.example.rest_microservice_facade_feignclient.handlers;

import com.example.rest_microservice_facade_feignclient.exception.UserRuntimeException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;


import java.io.IOException;
import java.io.InputStream;

public class CustomUserErrorDecoderImpl implements ErrorDecoder {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            InputStream body = response.body().asInputStream(); ;
            byte[] bytes = body.readAllBytes() ;
            UserRuntimeException userRuntimeException = objectMapper
                    .readValue(bytes, UserRuntimeException.class);
            System.out.println(userRuntimeException);
            return  userRuntimeException;
        } catch (IOException e) {
            throw new UserRuntimeException(400,
                    e.getMessage());
        }
    }
}

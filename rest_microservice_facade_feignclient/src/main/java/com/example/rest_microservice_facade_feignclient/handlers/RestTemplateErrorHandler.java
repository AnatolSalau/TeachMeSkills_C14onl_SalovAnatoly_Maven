package com.example.rest_microservice_facade_feignclient.handlers;


import com.example.rest_microservice_facade_feignclient.dto.ErrorDTO;
import com.example.rest_microservice_facade_feignclient.exception.UserRuntimeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;

/**
 * Implementation ResponseErrorHandler
 * We put that implementation in RestTemplateBuilder
 */
public class RestTemplateErrorHandler implements ResponseErrorHandler {
    // objectMapper for mapping ErrorDTO from bytes
    private final ObjectMapper objectMapper = new ObjectMapper();

    //Condition when we throw Exception
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        //Throw exception every time when code in not 200
        return !response.getStatusCode().is2xxSuccessful();
    }

    //How we handle Error when method of RestTemplate throw it
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        InputStream body = response.getBody();
        byte[] bytes = body.readAllBytes();
        ErrorDTO errorDTO = objectMapper.readValue(bytes, ErrorDTO.class);
        throw new UserRuntimeException(errorDTO.getStatusCode(),
                errorDTO.getMessages());
    }
}

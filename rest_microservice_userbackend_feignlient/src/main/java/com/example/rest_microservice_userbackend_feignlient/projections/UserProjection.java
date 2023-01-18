package com.example.rest_microservice_userbackend_feignlient.projections;

import java.util.UUID;

public interface UserProjection {
    UUID getId();
    String getLogin();
    String getPassword();
    String getEmail();
}

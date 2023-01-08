package com.example.criteria_jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class SearchRequest {
    private final String name;
    private final CarType carType;
    private final Boolean hasCar;
}

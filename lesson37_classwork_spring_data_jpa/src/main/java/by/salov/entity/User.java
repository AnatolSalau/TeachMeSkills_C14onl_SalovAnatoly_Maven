package by.salov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public class User {

    final static String STATIC_FIELD = "STATIC_FIELD";

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

}

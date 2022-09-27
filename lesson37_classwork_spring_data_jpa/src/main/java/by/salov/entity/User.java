package by.salov.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User {

    private static String STATIC_FIELD = "STATIC_FIELD";

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public static String getStaticField() {
        return STATIC_FIELD;
    }

    public Integer getAge() {
        return age;
    }

}

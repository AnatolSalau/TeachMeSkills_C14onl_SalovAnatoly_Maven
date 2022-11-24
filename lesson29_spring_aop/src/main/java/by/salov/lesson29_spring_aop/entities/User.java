package by.salov.lesson29_spring_aop.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@AllArgsConstructor

public class User {

    private Long id;

    private String login;

    private String firstName;

    public User(String login, String firstName) {
        this.login = login;
        this.firstName = firstName;
    }
}

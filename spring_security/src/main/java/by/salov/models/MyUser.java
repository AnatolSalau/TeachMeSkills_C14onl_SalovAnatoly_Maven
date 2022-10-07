package by.salov.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "my_users")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String login;
    private String password;
    private String position;
    private String role;
    //сеттеры/геттеры
}

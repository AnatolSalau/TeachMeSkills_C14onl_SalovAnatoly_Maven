package by.salov.lesson45_spring_security_roles.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;

    private String login;
    private String password;

    /**
     * CascadeType.ALL - means that it is necessary to cascade all operations at once
     * FetchType.LAZY - forces the ORM to load related entities and collections at once, along with the root entity
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id" ),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roleList = new ArrayList<>();
}

package com.example.spring_security_jwt_without_oauth.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "users")
@SequenceGenerator(sequenceName = "users_id_seq",
        name = "users_id_seq", allocationSize = 1)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType. SEQUENCE, generator =
            "users_id_seq")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @EqualsAndHashCode.Exclude
    @ManyToMany(
            cascade = {
                    CascadeType.ALL,
            },
            fetch = FetchType.EAGER
    )
    private Set<Role> roles = new HashSet<>() ;
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}

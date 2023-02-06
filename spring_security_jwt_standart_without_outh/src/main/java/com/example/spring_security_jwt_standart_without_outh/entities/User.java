package com.example.spring_security_jwt_standart_without_outh.entities;


import com.example.spring_security_jwt_standart_without_outh.enums.RolesAll;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@EqualsAndHashCode
@Getter

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

    @Column(unique = true, nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;


    @OneToMany(
            orphanRemoval = true,
            cascade ={ CascadeType.ALL},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private Set<Role> roles = new HashSet<>() ;

    public User(String login, String password, RolesAll role) {
        this.login = login;
        this.password = password;
        this.roles.add(new Role(role));
    }
}

package com.example.rest_microservice_userbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@NoArgsConstructor

@Entity
@Getter
@Service
public class User {

    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String login;
    private String password;
    private String email;

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    @CreationTimestamp
    private Date creationStamp;

    @UpdateTimestamp
    private Date updatingStamp;

    @Version
    private int versionStamp;
}

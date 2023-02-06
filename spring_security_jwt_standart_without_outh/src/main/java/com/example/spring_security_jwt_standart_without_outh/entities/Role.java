package com.example.spring_security_jwt_standart_without_outh.entities;

import com.example.spring_security_jwt_standart_without_outh.enums.RolesAll;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
@Getter

@Entity
@Table(name = "roles")
@SequenceGenerator(sequenceName = "roles_id_seq",
        name = "roles_id_seq", allocationSize = 1)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType. SEQUENCE, generator =
            "roles_id_seq")
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String role;

    public Role(RolesAll role) {
        this.role = role.toString();
    }
}

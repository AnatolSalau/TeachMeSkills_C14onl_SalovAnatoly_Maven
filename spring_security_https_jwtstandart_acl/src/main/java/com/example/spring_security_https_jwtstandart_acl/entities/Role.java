package com.example.spring_security_https_jwtstandart_acl.entities;


import com.example.spring_security_https_jwtstandart_acl.enums.RolesAll;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "roles")
@SequenceGenerator(sequenceName = "roles_id_seq", name = "roles_id_seq", allocationSize = 1)
public class Role {
      @Id
      @GeneratedValue(strategy = GenerationType. SEQUENCE, generator =
            "roles_id_seq")
      @Column(nullable = false)
      private Long id;
      @Column(nullable = false)
      private String role;

      public Role() {
      }

      public Role(RolesAll role) {
            this.role = role.toString() ;
      }

      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Role)) return false;
            Role role1 = (Role) o;
            return id.equals(role1.id) && role.equals(role1.role);
      }

      @Override
      public int hashCode() {
            return Objects.hash(id, role);
      }

      public Long getId() {
            return id;
      }

      public void setId(Long id) {
            this.id = id;
      }

      public String getRole() {
            return role;
      }

      public void setRole(String role) {
            this.role = role;
      }
}

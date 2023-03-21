package entities;


import enums.RolesAll;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name = "users")
@SequenceGenerator(sequenceName = "users_id_seq", name = "users_id_seq", allocationSize = 1)
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
            cascade ={ CascadeType. ALL} ,
            fetch = FetchType. EAGER
      )
      @JoinColumn(name = "user_id")
      @Column(nullable = false)
      private Set<Role> roles = new HashSet<>() ;

      public User() {
      }

      public User(String login, String password, RolesAll role) {
            this. login = login;
            this. password = password;
            this. roles.add(new Role(role)) ;
      }

      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;
            User user = (User) o;
            return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles);
      }

      @Override
      public int hashCode() {
            return Objects.hash(id, login, password, roles);
      }

      public Long getId() {
            return id;
      }

      public void setId(Long id) {
            this.id = id;
      }

      public String getLogin() {
            return login;
      }

      public void setLogin(String login) {
            this.login = login;
      }

      public String getPassword() {
            return password;
      }

      public void setPassword(String password) {
            this.password = password;
      }

      public Set<Role> getRoles() {
            return roles;
      }

      public void setRoles(Set<Role> roles) {
            this.roles = roles;
      }
}
package by.salov.spring_data_jpa_flydb_postgres.entities;

import by.salov.spring_data_jpa_flydb_postgres.enums.UserRole;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "admins")
@SequenceGenerator(sequenceName="admins_id_seq", name="admins_id_seq", allocationSize=1)
public class Admin extends User  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admins_id_seq")
    private Long id;
}

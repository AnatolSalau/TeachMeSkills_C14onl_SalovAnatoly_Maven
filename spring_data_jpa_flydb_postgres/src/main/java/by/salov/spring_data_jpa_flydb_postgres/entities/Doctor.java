package by.salov.spring_data_jpa_flydb_postgres.entities;

import by.salov.spring_data_jpa_flydb_postgres.enums.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "doctors")
@SequenceGenerator(sequenceName="doctors_id_seq", name="doctors_id_seq", allocationSize=1)
public class Doctor extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctors_id_seq")
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "doctors_patients",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private Set<Patient> patientSet = new HashSet<>();

}

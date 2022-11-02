package by.salov.spring_data_jpa_flydb_postgres.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "patient_glucose_level")
public class PatientGlucoseLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "level_glucose")
    private Integer levelGlucose;

    @OneToOne(mappedBy = "patientGlucoseLevel", cascade = CascadeType.ALL)
    private Patient patient;
}

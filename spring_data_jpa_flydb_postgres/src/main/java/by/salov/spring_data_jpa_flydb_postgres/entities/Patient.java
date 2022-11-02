package by.salov.spring_data_jpa_flydb_postgres.entities;

import by.salov.spring_data_jpa_flydb_postgres.enums.UserRole;

import javax.persistence.*;

@Entity
@Table(name = "patients")
public class Patient  extends User{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "glucose_level_id")
    private PatientGlucoseLevel patientGlucoseLevel;

    public Patient(Long id, String first_name, String second_name, UserRole userRole) {
        super(id, first_name, second_name, userRole);
    }

    public Patient() {

    }

}

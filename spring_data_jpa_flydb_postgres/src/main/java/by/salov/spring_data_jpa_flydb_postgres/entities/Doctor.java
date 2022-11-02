package by.salov.spring_data_jpa_flydb_postgres.entities;

import by.salov.spring_data_jpa_flydb_postgres.enums.UserRole;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor extends User{
    public Doctor(Long id, String first_name, String second_name, UserRole userRole) {
        super(id, first_name, second_name, userRole);
    }

    public Doctor() {

    }
}

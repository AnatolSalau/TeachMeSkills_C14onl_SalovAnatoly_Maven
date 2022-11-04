package by.salov.spring_data_jpa_flydb_postgres.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "patients")
@SequenceGenerator(sequenceName="patients_id_seq", name="patients_id_seq", allocationSize=1)
public class Patient  extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patients_id_seq")
    private Long id;

    /*foreignKey = @ForeignKey - Mark name of constraint*/
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "glucose_level_id", foreignKey = @ForeignKey(name = "fk_glucose_level_id"))
    private PatientGlucoseLevel patientGlucoseLevel;

    /*one-to-many unidirectional association*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patients_id", foreignKey = @ForeignKey(name = "fk_patients_id"))
    private List<Phone> phoneList;

    /*One-to-many bi-directional association*/
    /* Column name in mappedBy - strictly corresponds with the
       name of column in Adress
   */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
    private List<Adress> adresses;

    /*Create custom setter, to solve problem with save inverse side*/
    public void setAdresses(List<Adress> adresses) {
        if (adresses != null) {
            adresses.forEach( adress -> {
                adress.setPatient(this);
                /*or*/
/*                adress.setPatient(new Patient());*/
            });
        }
        this.adresses = adresses;
    }
}

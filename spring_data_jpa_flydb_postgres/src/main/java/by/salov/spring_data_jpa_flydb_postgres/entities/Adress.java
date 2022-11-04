package by.salov.spring_data_jpa_flydb_postgres.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode


@Entity
@Table(name = "addresses")
@SequenceGenerator(sequenceName="addresses_id_seq", name="addresses_id_seq", allocationSize=1)
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresses_id_seq")
    private Long id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "house_number")
    private String houseNumber;

    /*Bi-directional relation*/
    /*Change cascade = CascadeType.ALL on
    cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST
            CascadeType.REFRESH
    }
    to don't delete Person when we delete Adress
    * */
    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "patient_id", foreignKey = @ForeignKey(name = "fk_patients_id"))
    private Patient patient;
}

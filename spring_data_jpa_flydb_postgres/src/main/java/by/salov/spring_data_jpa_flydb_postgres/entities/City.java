package by.salov.spring_data_jpa_flydb_postgres.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "cities")
@SequenceGenerator(sequenceName="cities_id_seq", name="cities_id_seq", allocationSize=1)
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cities_id_seq")
    private Long id;

    @Column(name = "city_name")
    private String cityName;

}
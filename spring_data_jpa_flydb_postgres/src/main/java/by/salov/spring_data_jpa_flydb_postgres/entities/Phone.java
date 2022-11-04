package by.salov.spring_data_jpa_flydb_postgres.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "phones")
@SequenceGenerator(sequenceName="phones_id_seq", name="phones_id_seq", allocationSize=1)
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phones_id_seq")
    private Long id;

    @Column(name = "phone_number")
    private String cityName;
}

package com.example.criteria_jpa.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Entity
@Table(name = "car", schema = "public")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CarType carType;
    @Temporal(TemporalType.DATE)
    private Date dateCreationCar;
    @CreationTimestamp
    @Column(name = "creation_inside_database")
    private Date creationInsideDatabase;
    @UpdateTimestamp
    @Column(name = "updatind_inside_database")
    private Date updatingInsideDatabase;
    Boolean hasCar;
    @Version
    private int version;
    public Car(String name, CarType carType, Date dateCreationCar,Boolean hasCar) {
        this.name = name;
        this.carType = carType;
        this.dateCreationCar = dateCreationCar;
        this.hasCar = hasCar;
    }

    @PrePersist
    public void prePersist() {
        System.out.println("----------------------PRE PERSIST-----------------------------");
    }
    @PostPersist
    public void postPersist() {
        System.out.println("--------------------POST PERSIST-----------------------------");
    }
}
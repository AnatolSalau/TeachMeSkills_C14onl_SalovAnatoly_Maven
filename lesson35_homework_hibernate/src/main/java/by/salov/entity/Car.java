package by.salov.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cars")
public abstract class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

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

    @Type(type = "true_false")
    boolean hasCar;

    @Version
    private int version;

    public Car() {
    }

    public Car(String name, CarType carType, Date dateCreationCar, boolean hasCar) {
        this.name = name;
        this.carType = carType;
        this.dateCreationCar = dateCreationCar;
        this.hasCar = hasCar;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Date getDateCreationCar() {
        return dateCreationCar;
    }

    public void setDateCreationCar(Date dateCreationCar) {
        this.dateCreationCar = dateCreationCar;
    }

    public boolean isHasCar() {
        return hasCar;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }
}

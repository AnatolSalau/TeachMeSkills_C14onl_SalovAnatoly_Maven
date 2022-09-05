package by.salov.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DiscriminatorFormula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "car", schema = "public")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula( "case when parent_id is null then 'PARENT' ELSE 'CHILD' end" )
@DiscriminatorValue("CHILD")
public abstract class Car {
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

    public Car(Integer id, String name, CarType carType, Date dateCreationCar, Date creationInsideDatabase, Date updatingInsideDatabase, boolean hasCar, int version) {
        this.id = id;
        this.name = name;
        this.carType = carType;
        this.dateCreationCar = dateCreationCar;
        this.creationInsideDatabase = creationInsideDatabase;
        this.updatingInsideDatabase = updatingInsideDatabase;
        this.hasCar = hasCar;
        this.version = version;
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

    @Override
    public String toString() {
        return "Car{" +
                "parent_id=" + id +
                ", name='" + name + '\'' +
                ", carType=" + carType +
                ", dateCreationCar=" + dateCreationCar +
                ", creationInsideDatabase=" + creationInsideDatabase +
                ", updatingInsideDatabase=" + updatingInsideDatabase +
                ", hasCar=" + hasCar +
                ", version=" + version +
                '}';
    }
}

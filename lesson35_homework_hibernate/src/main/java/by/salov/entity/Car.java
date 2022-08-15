package by.salov.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;
    private String name;

    public Car() {
    }

    public Car(String name) {
        this.name = name;
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
}

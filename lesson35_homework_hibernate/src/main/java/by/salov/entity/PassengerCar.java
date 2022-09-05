package by.salov.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PassengerCar extends Car{
    private int quantityPeople;

    public PassengerCar() {
    }

    @Override
    public String toString() {
        return "PassengerCar{" +
                "quantityPeople=" + quantityPeople +
                ", hasCar=" + hasCar +
                '}';
    }

    public PassengerCar(String name, CarType carType, int quantityPeople, java.util.Date dateCreationCar, boolean hasCar) {
        super(name,carType, dateCreationCar, hasCar);
        this.quantityPeople = quantityPeople;
    }

    public PassengerCar(int quantityPeople) {
        this.quantityPeople = quantityPeople;
    }

    public PassengerCar(Integer number, String name, CarType carType, Date dateCreationCar, Date creationInsideDatabase, Date updatingInsideDatabase, boolean hasCar, int version, int quantityPeople) {
        super(number, name, carType, dateCreationCar, creationInsideDatabase, updatingInsideDatabase, hasCar, version);
        this.quantityPeople = quantityPeople;
    }

    public int getQuantityPeople() {
        return quantityPeople;
    }

    public void setQuantityPeople(int quantityPeople) {
        this.quantityPeople = quantityPeople;
    }
}

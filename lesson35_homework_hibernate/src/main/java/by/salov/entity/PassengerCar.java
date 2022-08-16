package by.salov.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

public class PassengerCar extends Car{
    private int quantityPeople;

    public PassengerCar() {
    }

    public PassengerCar(String name,CarType carType, int quantityPeople, java.util.Date dateCreationCar, boolean hasCar) {
        super(name,carType, dateCreationCar, hasCar);
        this.quantityPeople = quantityPeople;
    }

    @Override
    public String toString() {
        return "PassengerCar{" +
                "quantityPeople=" + quantityPeople +
                '}';
    }

    public int getQuantityPeople() {
        return quantityPeople;
    }

    public void setQuantityPeople(int quantityPeople) {
        this.quantityPeople = quantityPeople;
    }
}

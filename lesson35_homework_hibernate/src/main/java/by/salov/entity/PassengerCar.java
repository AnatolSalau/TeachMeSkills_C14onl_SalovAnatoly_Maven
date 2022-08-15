package by.salov.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users_one")
public class PassengerCar extends Car{
    private int quantityPeople;

    public PassengerCar() {
    }

    public PassengerCar(String name, int quantityPeople) {
        super(name);
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

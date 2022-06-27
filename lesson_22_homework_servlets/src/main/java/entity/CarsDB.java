package entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CarsDB  {
    Map<String, Car> cars = new HashMap<>();

    public CarsDB() {
    }

    public Map<String, Car> getCars() {
        return cars;
    }
    public void addCars(Car... cars) {
        Arrays.stream(cars)
                .peek(car -> {this.cars.put(car.getID(),car);});
    }
}

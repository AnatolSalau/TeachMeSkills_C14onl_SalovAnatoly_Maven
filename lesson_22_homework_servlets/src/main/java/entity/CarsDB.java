package entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CarsDB  {
    static Map<String, Car> carsMap = new HashMap<>();

    public static Map<String, Car> getCars() {
        return carsMap;
    }
    public static void addCars(Car... cars) {
        for ( Car car : cars ) {
            carsMap.put(car.getID(), car);
        }
    }
    public static void addCar(Car car) {
        carsMap.put(car.getID(), car);
    }

}

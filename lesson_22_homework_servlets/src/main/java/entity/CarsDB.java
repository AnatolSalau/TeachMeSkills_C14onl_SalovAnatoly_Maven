package entity;

import java.util.HashMap;
import java.util.Map;

public class CarsDB {
    private static Map<String, Car> carsMap = new HashMap<>();

    public static Map<String, Car> getCars() {
        return carsMap;
    }

    public static void addCars(Car... cars) {
        for (Car car : cars) {
            carsMap.put(car.getID(), car);
        }
    }

    public static void addCar(Car car) {
        carsMap.put(car.getID(), car);
    }

    public static void deleteCarById(String id) {
        carsMap.remove(id);
    }

    public static boolean updateCarById(Car car) {
        if (carsMap.containsKey(car.getID())) {
            carsMap.put(car.getID(), car);
            return true;
        } else {
            return false;
        }
    }
}

package services;

import entity.Car;

import java.io.PrintWriter;
import java.util.Map;

public class CarService {
    public static void printAllCarsOnWebPage(PrintWriter printWriter, Map<String, Car> cars) {
        printWriter.println("Cars: ");
        if (cars == null) {
            printWriter.println("null");
        } else {
            cars.forEach((s1, s2) -> {
                printWriter.println("Key: " + s1 + ", Value: " + s2.toString());
            });
        }
    }

    public static void printCarsByIdOnWebPage(PrintWriter printWriter, Map<String, Car> cars, String id) {
        printWriter.println("Cars with id: " + id);

        if (cars == null) {
            printWriter.println("null");
        } else if (cars.entrySet().stream().noneMatch(entry -> entry.getKey().equals(id))) {
            printWriter.println("This id is not including");
        } else {
            cars.entrySet().stream()
                    .filter(entry -> entry.getKey().equals(id))
                    .forEach(entry ->
                            printWriter.println(entry)
                    );
        }
    }

    public static Car parseCar(String[] arrParamsCar) {
        //Примем что id уникальный так что в массиве будет всегда параметры для одной машины
        //id = id,name,number
        Car car = null;
        if (arrParamsCar[0].isEmpty() && arrParamsCar[0].isBlank()) return car;
        String[] params = arrParamsCar[0].split(",");
        car = new Car(params[0], params[1], params[2]);
        return car;
    }
}

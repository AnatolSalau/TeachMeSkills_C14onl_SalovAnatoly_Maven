package by.salov.springboot_asynchronius.services;

import by.salov.springboot_asynchronius.entities.Car;
import by.salov.springboot_asynchronius.entities.City;
import by.salov.springboot_asynchronius.entities.User;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Service responding data from server
 */
@Service
@NoArgsConstructor
public class EntityService {
    public List<Car> getAllCars() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Car car1 = new Car("X1", "BMW");
        Car car2 = new Car("X2", "BMW");
        Car car3 = new Car("X3", "BMW");
        Car car4 = new Car("X4", "BMW");
        Car car5 = new Car("X5", "BMW");
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);
        return cars;
    }

    public List<City> getAllCities() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        City city1 = new City("Moscow1", "Russia");
        City city2 = new City("Moscow1", "Russia");
        City city3 = new City("Moscow1", "Russia");
        City city4 = new City("Moscow1", "Russia");
        City city5 = new City("Moscow1", "Russia");
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        cities.add(city4);
        cities.add(city5);
        return cities;
    }

    public List<User> getAllUsers() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        User user1 = new User("Dima1", "Dimich");
        User user2 = new User("Dima2", "Dimich");
        User user3 = new User("Dima3", "Dimich");
        User user4 = new User("Dima4", "Dimich");
        User user5 = new User("Dima5", "Dimich");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        return users;
    }
}

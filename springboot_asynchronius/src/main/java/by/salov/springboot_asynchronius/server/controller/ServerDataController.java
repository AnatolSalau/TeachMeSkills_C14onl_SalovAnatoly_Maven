package by.salov.springboot_asynchronius.server.controller;

import by.salov.springboot_asynchronius.entities.Car;
import by.salov.springboot_asynchronius.entities.City;
import by.salov.springboot_asynchronius.entities.User;
import by.salov.springboot_asynchronius.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController()
@RequestMapping(value = "/server")
public class ServerDataController {
    @Autowired
    private EntityService entityService;

    @GetMapping(value = "/getcars")
    public List<Car> getAllCars() throws InterruptedException, ExecutionException {
        List<Car> allCars = entityService.getAllCars();
        return allCars;
    }

    @GetMapping(value = "/getcities")
    public List<City> getAllCities() throws InterruptedException {
        return entityService.getAllCities();
    }

    @GetMapping(value = "/getusers")
    public List<User> getAllUsers() throws InterruptedException {
        return entityService.getAllUsers();
    }
}

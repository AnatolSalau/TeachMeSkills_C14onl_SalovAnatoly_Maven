package by.salov.springboot_asynchronius.client.controller;


import by.salov.springboot_asynchronius.entities.Car;
import by.salov.springboot_asynchronius.entities.City;
import by.salov.springboot_asynchronius.entities.User;
import by.salov.springboot_asynchronius.services.AsyncDataService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/client")
public class ClientDataController {

    @Autowired
    AsyncDataService asyncDataService;

    @GetMapping(value = "/getdata")
    public List<Object> getData() throws InterruptedException, ExecutionException {
        System.out.println(System.currentTimeMillis());
        CompletableFuture<List<Car>> futureCarList = asyncDataService.getFutureCarList();
        CompletableFuture<List<City>> futureCityList = asyncDataService.getFutureCityList();
        CompletableFuture<List<User>> futureUserList = asyncDataService.getFutureUserList();


        List<Car> cars = futureCarList.get();
        System.out.println(cars);
        List<City> cities = futureCityList.get();
        System.out.println(cities);
        List<User> users = futureUserList.get();
        System.out.println(users);
        List<Object> result = new ArrayList<>();
        result.addAll(cars);
        result.addAll(cities);
        result.addAll(users);
        System.out.println(System.currentTimeMillis());
        return result;
    }

}

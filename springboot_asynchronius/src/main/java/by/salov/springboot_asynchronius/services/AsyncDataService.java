package by.salov.springboot_asynchronius.services;

import by.salov.springboot_asynchronius.entities.Car;
import by.salov.springboot_asynchronius.entities.City;
import by.salov.springboot_asynchronius.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncDataService {

    private RestTemplate restTemplate;


    @Autowired
    public AsyncDataService() throws InterruptedException {
        this.restTemplate = new RestTemplate();
    }

    @Async
    public CompletableFuture<List<Car>> getFutureCarList() {
        CompletableFuture<List<Car>> futureList = CompletableFuture.completedFuture(restTemplate.
                getForObject("http://127.0.0.1:8080/server/getcars", List.class));
        return futureList;
    }

   @Async
    public CompletableFuture<List<City>> getFutureCityList() {
        CompletableFuture<List<City>> futureList = CompletableFuture.completedFuture(restTemplate.
                getForObject("http://127.0.0.1:8080/server/getcities", List.class));
        return futureList;
    }

    @Async
    public CompletableFuture<List<User>> getFutureUserList() {
        CompletableFuture<List<User>> futureList = CompletableFuture.completedFuture(restTemplate.
                getForObject("http://127.0.0.1:8080/server/getusers", List.class));
        return futureList;
    }
}

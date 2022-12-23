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
import java.util.concurrent.ExecutionException;

/**
 * Asynchronous service for async requests
 */
@Service
public class AsyncDataService {

    private RestTemplate restTemplate;


    @Autowired
    public AsyncDataService() throws InterruptedException {
        this.restTemplate = new RestTemplate();
    }
    //Insert name executor from Configuration
    @Async(value = "CustomExecutor")
    public CompletableFuture<List<Car>> getFutureCarList() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Car>> futureList = CompletableFuture.completedFuture(restTemplate.
                getForObject("http://127.0.0.1:8080/server/getcars", List.class));
        System.out.println("Return " + futureList.get());
        System.out.println("From Thread " + Thread.currentThread().getName());
        return futureList;
    }

    @Async(value = "CustomExecutor")
    public CompletableFuture<List<City>> getFutureCityList() throws ExecutionException, InterruptedException {
        CompletableFuture<List<City>> futureList = CompletableFuture.completedFuture(restTemplate.
                getForObject("http://127.0.0.1:8080/server/getcities", List.class));
        System.out.println("Return " + futureList.get());
        System.out.println("From Thread " + Thread.currentThread().getName());
        return futureList;
    }

    @Async(value = "CustomExecutor")
    public CompletableFuture<List<User>> getFutureUserList() throws ExecutionException, InterruptedException {
        CompletableFuture<List<User>> futureList = CompletableFuture.completedFuture(restTemplate.
                getForObject("http://127.0.0.1:8080/server/getusers", List.class));
        System.out.println("Return " + futureList.get());
        System.out.println("From Thread " + Thread.currentThread().getName());
        return futureList;
    }
}

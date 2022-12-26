package by.salov.springboot_asynchronius.services;

import by.salov.springboot_asynchronius.entities.Car;
import by.salov.springboot_asynchronius.entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ScheduleService {
    private RestTemplate restTemplate;

    @Autowired()
    public ScheduleService() throws InterruptedException {
        this.restTemplate = new RestTemplate();
        System.out.println("ScheduleService created - " + new Date());
    }

    /**
     * @Scheduled(fixedRate = 1000) - run this method every seconds
     * initialDelay = 5000 - wait5 sec before as run method
     */
    @Scheduled(initialDelay = 7000, fixedRate = 7000)
    public List<Car> getAllCarScheduled() throws ExecutionException, InterruptedException {
        System.out.println("getAllCarScheduled start - " + new Date() + Thread.currentThread().getName());
        CompletableFuture<List<Car>> futureList = CompletableFuture.completedFuture(restTemplate.
                getForObject("http://127.0.0.1:8080/server/getcars", List.class));
        List<Car> cars = futureList.get();
        System.out.println("getAllCarScheduled end - " + new Date() + Thread.currentThread().getName());
        System.out.println(cars);
        return cars;
    };
/*
    *//**
     * Schedule with cron
     *//*
    @Scheduled(cron = "30 9 ? * MON-FRI")
    public List<City> getAllCitiesScheduled() throws ExecutionException, InterruptedException {
        System.out.println("getAllCarScheduled start - " + new Date());
        CompletableFuture<List<City>> futureList = CompletableFuture.completedFuture(restTemplate.
                getForObject("http://127.0.0.1:8080/server/getcities", List.class));
        List<City> cars = futureList.get();
        System.out.println("getAllCarScheduled end - " + new Date());
        System.out.println(cars);
        return cars;
    };*/
}

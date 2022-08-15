package by.salov.services;

import by.salov.entity.PassengerCar;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class StartupService  {
    CarCreateReadUpdateDeleteService createReadUpdateDeleteService;

    public StartupService(CarCreateReadUpdateDeleteService createReadUpdateDeleteService) {
        this.createReadUpdateDeleteService = createReadUpdateDeleteService;
    }
    @PostConstruct
    public void start() {
        PassengerCar passengerCar = new PassengerCar("name1",4);
        //createReadUpdateDeleteService.create(passengerCar);
        createReadUpdateDeleteService.update(1);
    }
}

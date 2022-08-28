package by.salov.services;

import by.salov.entity.CarType;
import by.salov.entity.PassengerCar;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


@Service
public class StartupService {
    CarCreateReadUpdateDeleteService createReadUpdateDeleteService;

    public StartupService(CarCreateReadUpdateDeleteService createReadUpdateDeleteService) {
        this.createReadUpdateDeleteService = createReadUpdateDeleteService;
    }

    @PostConstruct
    public void start() {
        java.util.Date dateCarCreation1 = null;
        java.util.Date dateCarCreation2 = null;
        java.util.Date dateCarCreation3 = null;

        try {
            dateCarCreation1 = DateService.stringToDateConvert("01-JAN-1990");
            dateCarCreation2 = DateService.stringToDateConvert("10-FEB-2000");
            dateCarCreation3 = DateService.stringToDateConvert("20-SEP-2010");

        } catch (ParseException exception) {
            exception.printStackTrace();
        }

        PassengerCar passengerCar = new PassengerCar(
                "Name2", CarType.BMW, 6, dateCarCreation1, false);
        PassengerCar passengerCar2 = new PassengerCar(
                "Name3", CarType.MERCEDES, 4, dateCarCreation1, false);
        PassengerCar passengerCar3 = new PassengerCar(
                "Name4", CarType.AUDI, 2, dateCarCreation1, true);

        createReadUpdateDeleteService.create(passengerCar);
        createReadUpdateDeleteService.create(passengerCar2);
        createReadUpdateDeleteService.create(passengerCar3);

        //createReadUpdateDeleteService.update(2, 10, CarType.AUDI, dateCarCreation3, true);
        //createReadUpdateDeleteService.getAllCars();
    }


}

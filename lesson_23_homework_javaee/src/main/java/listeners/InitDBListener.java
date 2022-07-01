package listeners;

import entity.Car;
import entity.CarsDB;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InitDBListener implements ServletContextListener {
    public InitDBListener() {
        CarsDB.addCars(
                new Car("0001", "Mercedes", "A01"),
                new Car("0002", "BMW", "B02"),
                new Car("0003", "Audi", "C03"),
                new Car("0004", "Volvo", "D04")
        );
    }
}

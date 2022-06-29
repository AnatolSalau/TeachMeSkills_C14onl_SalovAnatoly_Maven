package servlets;

import entity.Car;
import entity.CarsDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class InitDBServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        CarsDB carsDB = new CarsDB();
        carsDB.addCars(
                new Car("0001", "Mercedes", "A01"),
                new Car("0002", "BMW", "B02"),
                new Car("0003", "Audi", "C03"),
                new Car("0004", "Volvo", "D04")
        );
    }
}

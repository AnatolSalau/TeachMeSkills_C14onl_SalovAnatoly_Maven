package servlets;

import entity.Car;
import entity.CarsDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.CarService;
import services.CookiesService;
import services.TimeService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class PutCarServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> allParams = req.getParameterMap();
        PrintWriter printWriter = resp.getWriter();
        //Работаем с куками
        resp.addCookie(new Cookie("lastTime", new TimeService().get()));
        Map<String, String> allCookies = CookiesService.getMapCookies(req);
        CookiesService.printOnWebPage(printWriter, allCookies);
        //Обновляем параметры
        //Добавим парметры в СarDB
        for (String[] arr : allParams.values()) {
            Car car = CarService.parseCar(arr);
            if (car == null) {
                printWriter.println("Car parameters is wrong");
            }
            if (CarsDB.updateCarById(car) == false) {
                printWriter.println(car.getID() + " not found");
            }
        }
    }
}

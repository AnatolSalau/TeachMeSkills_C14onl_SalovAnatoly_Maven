package servlets;

import entity.Car;
import entity.CarsDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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

@WebServlet(value = "/post")
public class PostCarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> allParams = req.getParameterMap();
        PrintWriter printWriter = resp.getWriter();
        //Работаем с куками
        resp.addCookie(new Cookie("lastTime", new TimeService().get()));
        Map<String, String> allCookies = CookiesService.getMapCookies(req);
        CookiesService.printOnWebPage(printWriter, allCookies);
        //Запрос на добавление машины будет выглядеть так
        //Примем что id уникальный -> так что в массиве будет всегда параметры для одной машины
        //id машины совпадает с ключом параметра
        //id = id,name,number
        //Key: 0004, Value: Car{ID=0004, name='Volvo', number='D04'}
        //Добавим парметры в СarDB
        for (String[] arr : allParams.values()) {
            Car car = CarService.parseCar(arr);
            CarsDB.addCar(car);
        }
    }
}

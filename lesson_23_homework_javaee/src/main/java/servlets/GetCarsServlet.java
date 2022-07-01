package servlets;

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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(value = "/get")
public class GetCarsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> allParams = req.getParameterMap();
        PrintWriter printWriter = resp.getWriter();
        //Работаем с куками
        resp.addCookie(new Cookie("lastTime", new TimeService().get()));
        Map<String, String> allCookies = CookiesService.getMapCookies(req);
        CookiesService.printOnWebPage(printWriter, allCookies);
        //Проверяем какие ключи параметров есть
        //all - получить все машины
        //id=0003 - получить машину с id 0003
        boolean isGetAll = false;
        boolean isGetById = false;
        //Проверяем есть ли вообще такие ключи
        isGetAll = allParams.entrySet().stream().
                anyMatch(entry -> entry.getKey().equals("all"));

        isGetById = allParams.entrySet().stream().
                anyMatch(entry -> entry.getKey().equals("id") && entry.getValue() != null);
        //Печатаем все машины
        if (isGetAll) {
            CarService.printAllCarsOnWebPage(printWriter, CarsDB.getCars());
        }
        //Печатаем машины по ID
        if (isGetById) {
            //Все ID
            List<String> allId = allParams.entrySet().stream()
                    .filter(entry -> entry.getKey().equals("id"))
                    .flatMap(entry -> Arrays.stream(entry.getValue()))
                    .collect(Collectors.toList());
            printWriter.println("All entered id" + allId);
            //Печатаем все машины с ID
            allId.stream().forEach(
                    id -> CarService.printCarsByIdOnWebPage(printWriter, CarsDB.getCars(), id));
        }
    }
}

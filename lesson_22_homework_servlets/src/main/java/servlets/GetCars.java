package servlets;

import entity.CarsDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.CarService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetCars extends HttpServlet {
    protected Map<String, String[]> allParams;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        allParams = req.getParameterMap();
        PrintWriter printWriter =resp.getWriter();
        //Проверяем какие ключи параметров есть
        //all - получить все машины
        //id - получить определенную
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
         };
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
         };

    }

}

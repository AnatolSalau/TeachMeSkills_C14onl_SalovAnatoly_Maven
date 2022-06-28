package servlets;

import entity.Car;
import entity.CarsDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.CookiesService;
import services.TimeService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class PostCarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> allParams = req.getParameterMap();
        PrintWriter printWriter =resp.getWriter();
        //Работаем с куками
        resp.addCookie(new Cookie("lastTime", new TimeService().get()));
        Map<String, String> allCookies = CookiesService.getMapCookies(req);
        CookiesService.printOnWebPage(printWriter, allCookies );
        for (String[] arr:allParams.values()) {
            printWriter.println(Arrays.toString(arr));
        }
        //Запрос на добавление машины будет выглядеть так
        //car = {"id": "00010", "name": "Lada", "number": "010OE"}

    }
}

package servlets;

import by.salov.entity.CarsDB;
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

public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();

        resp.addCookie(new Cookie("lastTime", new TimeService().get()));

        Map<String, String> allCookies = CookiesService.getMapCookies(req);
        CookiesService.printOnWebPage(printWriter, allCookies);

        CarService.printAllCarsOnWebPage(printWriter, CarsDB.getCars());
    }
}

package servlets;

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

import java.util.Map;

public class DeleteCarServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> allParams = req.getParameterMap();
        PrintWriter printWriter = resp.getWriter();
        //Работаем с куками
        resp.addCookie(new Cookie("lastTime", new TimeService().get()));
        Map<String, String> allCookies = CookiesService.getMapCookies(req);
        CookiesService.printOnWebPage(printWriter, allCookies);
        //Удаляем по ключам параметров которые должны быть равны id
        allParams.forEach(
                (s, strings) -> CarsDB.deleteCarById(s)
        );
    }
}

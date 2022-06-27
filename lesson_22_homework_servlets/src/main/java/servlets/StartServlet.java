package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.GetCookiesService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        resp.addCookie(new Cookie("lastTime", ""));
        printWriter.println("Start servlet");
        Map<String, String> allCookies = GetCookiesService.getMapCookies(req);
        GetCookiesService.printOnPage(printWriter, allCookies );
    }
}

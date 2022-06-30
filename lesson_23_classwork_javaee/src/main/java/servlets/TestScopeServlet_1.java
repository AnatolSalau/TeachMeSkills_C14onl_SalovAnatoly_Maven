package servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
//Мапим сервлет на .../scope1
@WebServlet(value = "/scope1")
public class TestScopeServlet_1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        System.out.println("attribute from servlet context scope 1" + servletContext.getAttribute("att"));//null
        //Устанавливаем значение
        servletContext.setAttribute("att", "value from servletContext");
        System.out.println("attribute from servlet context after set scope1" + servletContext.getAttribute("att"));
    }
}

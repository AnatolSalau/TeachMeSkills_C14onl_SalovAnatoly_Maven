package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlets.util.PrintOnPage;
import jakarta.servlet.ServletRegistration.Dynamic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(value = "")
public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("http://127.0.0.1:8080/lesson24homework/login.html");
    }
}
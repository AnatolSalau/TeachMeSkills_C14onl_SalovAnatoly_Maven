package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlets.util.PrintOnPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(value = "/errorpage")
public class ErrorPageServlet extends HttpServlet {
    Object att = null;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        att = req.getAttribute("login");
        resp.sendRedirect("http://127.0.0.1:8080/lesson25homework/errorpage.html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        String login = (String) att;
        printWriter.println("User with login : " + login + "already exist");
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("{\"user\": " + "{");
        jsonString.append(
                "\"login\" : "+ "\"" + login+ "\""
        );
        jsonString.append(" } }");
        printWriter.print(jsonString);
    }
}

package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(value = "/errorpage")
public class ErrorPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] logins = parameterMap.get("login");
        printWriter.print("User with login ");
        for (String login : logins) {
            printWriter.print(login + " ");
        }
        printWriter.println("already exists");
    }
}

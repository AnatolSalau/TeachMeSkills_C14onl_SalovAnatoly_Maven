package servlets;

import enums.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.ValidationUser;
import servlets.util.PrintOnPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    ValidationUser validationUser = new ValidationUser();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("http://127.0.0.1:8080/lesson24homework/login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        PrintWriter printWriter = resp.getWriter();
        PrintOnPage<String, String> printOnPage = new PrintOnPage<>();
        printOnPage.printMapOnPageByPrintWriter(printWriter, parameterMap);

        if (validationUser.isCorrect(
                req.getParameter("login"), req.getParameter("password"), Role.ADMIN)
        ) {
            resp.sendRedirect("http://127.0.0.1:8080/lesson24homework/registration.html");
        }
        else {
            resp.sendRedirect("http://127.0.0.1:8080/lesson24homework/picture.html");
        }


    }
}

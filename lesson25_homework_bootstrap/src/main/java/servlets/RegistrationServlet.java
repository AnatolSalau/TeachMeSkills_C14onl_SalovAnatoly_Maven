package servlets;

import dao.DBUsersConnect;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.ValidationUser;
import servlets.util.PrintOnPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("http://127.0.0.1:8080/lesson25homework/registration.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        DBUsersConnect dbUsersConnect = new DBUsersConnect();
        ValidationUser validationUser = new ValidationUser();
        String[] logins = parameterMap.get("login");
        boolean hasUser = true;
        hasUser = Arrays.stream(logins)
                .map(validationUser::hasUser)
                .findFirst()
                .orElse(false);
        System.out.println("hasUser = " + hasUser);

        if (!hasUser) {
            User newUser = new User(
                    parameterMap.get("login")[0], parameterMap.get("password")[0], parameterMap.get("gender")[0], parameterMap.get("description")[0], parameterMap.get("role")[0]
            );
            dbUsersConnect.addUser(newUser);
            resp.sendRedirect("http://127.0.0.1:8080/lesson25homework/allusers");
        } else {
            req.setAttribute("login",parameterMap.get("login")[0]);
            System.out.println(req.getAttribute("login"));
            String attribute= (String)req.getAttribute("login");
            System.out.println(attribute);
            req.getRequestDispatcher("/errorpage").forward(req, resp);
        }
    }
}

package servlets;

import dao.DBUsersConnect;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.ValidationUser;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("http://127.0.0.1:8080/lesson24homework/registration.html");
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
            req.getRequestDispatcher("/allusers").forward(req, resp);
        } else {
            req.getRequestDispatcher("/errorpage").forward(req, resp);
        }
    }
}

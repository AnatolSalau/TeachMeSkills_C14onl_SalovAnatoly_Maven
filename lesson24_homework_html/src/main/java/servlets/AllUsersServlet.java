package servlets;

import dao.DBUsersConnect;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(value = "/allusers")
public class AllUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("application/json");
        String employeeJsonString = """
                {"firstName": "Json", "lastName": "Smith", "age": 30}
                """;
        printWriter.print(employeeJsonString);
/*        PrintWriter printWriter = resp.getWriter();
        DBUsersConnect dbUsersConnect = new DBUsersConnect();
        Map<String, User> allUsers = dbUsersConnect.getAllUsers();
        allUsers.forEach((key, value) ->
                printWriter.println("key : " + key + " value : " + value.toString()));*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        DBUsersConnect dbUsersConnect = new DBUsersConnect();
        Map<String, User> allUsers = dbUsersConnect.getAllUsers();
        allUsers.forEach((key, value) ->
                printWriter.println("key : " + key + " value : " + value.toString()));
    }
}

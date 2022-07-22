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

//Сервлет создает JSON на своем url
@WebServlet(value = "/allusers")
public class AllUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        //Указываем что информация на странице это json
        resp.setContentType("application/json");
        //Пример JSON
/*        String employeeJsonString = """
                {"users": {"Second" : {"login" : "Second", "password" : "aaaa"}, "First" : {"login" : "First", "password" : "1111"}  } }
                """;*/
        DBUsersConnect dbUsersConnect = new DBUsersConnect();
        Map<String, User> allUsers = dbUsersConnect.getAllUsers();
        //Create JSONString
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("{\"users\": " + "{");
        allUsers.forEach((key, value) ->
                jsonString.append(
                        ", \"" + key+ "\"" + " : "+"{"
                                + "\"login\" : "+ "\"" + value.getLogin()+ "\"" + ", "
                                + "\"password\" : "+ "\"" + value.getPassword()+ "\""+ ", "
                                + "\"gender\" : "+ "\"" + value.getGender()+ "\""+ ", "
                                + "\"description\" : "+ "\"" + value.getDescription()+ "\""+ ", "
                                + "\"role\" : "+ "\"" + value.getRole()+ "\""
                                + "} "
                ));
        jsonString.append(" } }");
        //Remove extra comma (Удаляем лишнюю запятую которая недает прочесть JSON в JS)
        jsonString.deleteCharAt(11);
        if (jsonString != null) {
            //Печатаем на странице
            printWriter.print(jsonString);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        //Указываем что информация на странице это json
        resp.setContentType("application/json");
        //Пример JSON
/*        String employeeJsonString = """
                {"users": {"Second" : {"login" : "Second", "password" : "aaaa"}, "First" : {"login" : "First", "password" : "1111"}  } }
                """;*/
        DBUsersConnect dbUsersConnect = new DBUsersConnect();
        Map<String, User> allUsers = dbUsersConnect.getAllUsers();
        //Create JSONString
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("{\"users\": " + "{");
        allUsers.forEach((key, value) ->
                jsonString.append(
                        ", \"" + key+ "\"" + " : "+"{"
                                + "\"login\" : "+ "\"" + value.getLogin()+ "\"" + ", "
                                + "\"password\" : "+ "\"" + value.getPassword()+ "\""+ ", "
                                + "\"gender\" : "+ "\"" + value.getGender()+ "\""+ ", "
                                + "\"description\" : "+ "\"" + value.getDescription()+ "\""+ ", "
                                + "\"role\" : "+ "\"" + value.getRole()+ "\""
                                + "} "
                ));
        jsonString.append(" } }");
        //Remove extra comma (Удаляем лишнюю запятую которая недает прочесть JSON в JS)
        jsonString.deleteCharAt(11);
        if (jsonString != null) {
            //Печатаем на странице
            printWriter.print(jsonString);
        }
    }
}

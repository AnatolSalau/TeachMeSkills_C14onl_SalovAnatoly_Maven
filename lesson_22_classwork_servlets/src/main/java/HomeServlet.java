import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException, IOException {
        //Берем у request параметр
        // http://127.0.0.1:8080/lesson22/book?app=test
        //?app=test - связка ключ значение
        //Можно передавать несколько значений через &
        //http://127.0.0.1:8080/lesson22/book?app=nameApp&os=macMini
        String app = req.getParameter("app");
        String os = req.getParameter("os");

        PrintWriter writer = resp.getWriter();
        writer.println("Hello from servlet app = " + app + " os = " + os);
    }
}

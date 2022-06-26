import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Перенаправим запрос на другой сервлет нашего приложения  <url-pattern>/end</url-pattern>
        //req.getRequestDispatcher("/end").forward(req,resp);
        //Получим ответ из другого servlet

        //Получим куки и распечатаем
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("StartServlet");
/*        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            Arrays.stream(cookies).forEach(cookie -> printWriter.println(
                    "value : "+ cookie.getValue()
            ));
        }*/
        resp.addCookie(new Cookie("firstCokie", "value of fist cokie"));
    }
}

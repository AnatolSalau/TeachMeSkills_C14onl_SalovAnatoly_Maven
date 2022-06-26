import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EndServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("os");
        resp.getWriter().println("parameter os = " + parameter);
        //resp.sendRedirect("http://google.ru"); - перенаправляет на другой url
        //resp.sendRedirect("http://google.ru");
    }
}

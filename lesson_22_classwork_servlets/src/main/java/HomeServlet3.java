import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HomeServlet3 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Init");
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Service");
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("Method do get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("Method do post");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("doDelete");
        //Добавляем в header параметр user
        resp.setHeader("user", "doDelete");
        //Меняем код статуса
        resp.setStatus(204);
    }

    @Override
    public void destroy() {
        System.out.println("Destroy");
        super.destroy();
    }
}

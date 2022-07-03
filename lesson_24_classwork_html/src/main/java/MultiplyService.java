import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

@WebServlet(value = "/multiply")
public class MultiplyService extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        //Получаем массив значений из <select multiply>
        String[] carsValues = req.getParameterValues("cars");
        printWriter.println("req.getParameterValues");
        printWriter.println(Arrays.toString(carsValues));
        //Получаем массив имен параметров
        Enumeration<String> carsNames = req.getParameterNames();
        printWriter.println("req.getParameterNames");
        printWriter.println(Collections.list(carsNames));
    }
}

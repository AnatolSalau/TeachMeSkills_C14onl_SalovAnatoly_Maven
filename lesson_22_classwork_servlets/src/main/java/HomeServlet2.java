import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

public class HomeServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException, IOException {
        //Берем у request параметры и сохраняем в MAP
        Map<String, String[]> parameterMap = req.getParameterMap();
        //Перебираем map и сохраняем в строку
        StringBuilder stringBuilder = new StringBuilder();
        parameterMap.forEach(((key, value) -> stringBuilder.append(
                key + " : " + Arrays.toString(value) +", "
        )));
        PrintWriter writer = resp.getWriter();
        writer.println("HomeServlet2 parameters: " + stringBuilder);

        //Берем у request headers
        StringBuilder stringHeaders = new StringBuilder();
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = req.getHeader(key);
            stringHeaders.append( key + " : " + value +", ");
        }
        //Печатаем headers на странице
        writer.println("Home servlet 2 headers: " + stringHeaders);
    }
}

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/addfile")
//Указываем что сервлет работает с частями
@MultipartConfig
public class AddFileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text");
        String password = req.getParameter("password");
        String number = req.getParameter("number");


        resp.getWriter().println(text);
        resp.getWriter().println(password);
        resp.getWriter().println(number);

        //Получаем отправленный файл
        Part file = req.getPart("file");
        InputStream inputStream = file.getInputStream();
        byte[] bytes = inputStream.readAllBytes();
        //Base64 -система кодирования и декодирования в ASCII
        String str = Base64.getEncoder().encodeToString(bytes);
        resp.getWriter().println(str);
        //Читаем файл в кодировке
        String str2 = new String(bytes,"UTF-8");
        resp.getWriter().println(str2);
    }
}

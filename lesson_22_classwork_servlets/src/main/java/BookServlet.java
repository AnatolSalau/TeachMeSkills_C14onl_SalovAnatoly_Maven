import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String payLoad = """
                 <!DOCTYPE html>
                <html>
                <body>
                                
                <h1>My First Heading</h1>
                <p>My first paragraph.</p>
                                
                </body>
                </html>
                """;
        writer.println(payLoad);
    }
}

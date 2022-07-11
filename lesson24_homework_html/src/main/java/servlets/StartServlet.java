package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlets.util.PrintOnPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(value = "/login")
public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        PrintWriter printWriter = resp.getWriter();
        PrintOnPage<String,String> printOnPage = new PrintOnPage<>();
        printOnPage.printMapOnPageByPrintWriter(printWriter,parameterMap);
    }


}

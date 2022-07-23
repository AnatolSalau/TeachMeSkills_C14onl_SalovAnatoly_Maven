package servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

//Servlet for display image in html
@WebServlet(value = "/image")
public class DisplayImageServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // set the content type to image/jpeg for displaying image in browser
        response.setContentType("image/jpeg");
        //Get file separator
        String separator = File.separator;
        ServletContext servletContext = request.getServletContext();
        //Get path relative tomcat hierarchy folders after deploying ()
        String realPath = servletContext.getRealPath("WEB-INF" + separator + "classes" + separator + "images" + separator + "comp.jpeg");
        FileInputStream fileInputStream = new FileInputStream(realPath);
        // getting image in BufferedInputStream
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        bufferedOutputStream.write(bufferedInputStream.readAllBytes());
        bufferedOutputStream.close();
        bufferedOutputStream.close();
    }
}

package by.salov.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;

//We can display our html page from static resources just like bytes by @RestController or @ResponseBody annotation
@Controller
public class ResponseBodyController {
    @RequestMapping("/gethtml")
    @ResponseBody
    //https://stackoverflow-com.translate.goog/questions/38307959/spring-mvc-what-is-an-httpentity?_x_tr_sl=auto&_x_tr_tl=ru&_x_tr_hl=ru
    //HttpEntity (and the response-specific subclass ResponseEntity) also allows access to the request and response headers
    public HttpEntity<byte[]> getHtml(HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("WEB-INF/resources/html") +"/test.html";;
        System.out.println(path);
        FileInputStream file = new FileInputStream(path);
        byte[] image = file.readAllBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        headers.setContentLength(image.length);
        return new HttpEntity<byte[]>(image, headers);
    }
    @RequestMapping("/getimage")
    @ResponseBody
    public HttpEntity<byte[]> getImage(HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("WEB-INF/resources/images") +"/car.jpg";
        System.out.println(path);
        FileInputStream file = new FileInputStream(path);
        byte[] image = file.readAllBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new HttpEntity<byte[]>(image, headers);
    }
}

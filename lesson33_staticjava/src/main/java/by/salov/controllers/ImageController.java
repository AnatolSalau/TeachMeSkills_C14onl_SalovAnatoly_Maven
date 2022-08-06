package by.salov.controllers;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    @RequestMapping("/gethtml")
    @ResponseBody
    public HttpEntity<byte[]> getHtml(HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("resources/html") +"/test.html";;
        System.out.println(path);
        FileInputStream file = new FileInputStream(path);
        System.out.println(file);
        byte[] image = file.readAllBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        headers.setContentLength(image.length);
        return new HttpEntity<byte[]>(image, headers);
    }
    @RequestMapping("/getimage")
    @ResponseBody
    public HttpEntity<byte[]> getImage(HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("resources/images") +"/car.jpg";
        System.out.println(path);
        FileInputStream file = new FileInputStream(path);
        System.out.println(file);
        byte[] image = file.readAllBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new HttpEntity<byte[]>(image, headers);
    }
}

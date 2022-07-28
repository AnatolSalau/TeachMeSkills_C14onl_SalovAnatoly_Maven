package by.salov.controllers;

import org.apache.maven.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(path = "/book")
public class BookController  {

    @GetMapping("/search")
    public String getBookSearch(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException, ServletException {
        System.out.println("---------------SEARCH BOOK GET HOME-----------------");
        //httpServletRequest.getRequestDispatcher
        //httpServletRequest.getRequestDispatcher("/book").forward(httpServletRequest,httpServletResponse);
        //We can use sendRedirect httpServletResponse.sendRedirect
        //httpServletResponse.sendRedirect("http://127.0.0.1:8080/lesson31classwork/");
        return "searchbook";
    }
    @GetMapping
    public String getBook() {
        System.out.println("---------------HOME BOOK-----------------");
        return "bookHome";
    }
    @PostMapping
    public String postBook(HttpServletRequest httpServletRequest,
                           //Params
                           @RequestParam(name = "email")String emailServ ,
                           @RequestParam(name = "password")String passwordServ,
                           //Headers
                           @RequestHeader("User-Agent")String headerServ
        ) {
            System.out.println("---------------POST BOOK-----------------");
            //We can work like HttpServletRequest
            String email = httpServletRequest.getParameter("email");
            String password = httpServletRequest.getParameter("password");
        String header = httpServletRequest.getHeader("User-Agent");
        System.out.println(header);
        //Spring get parameter by annotation @RequestParam(name = "email")
        //If we don't get RequestParam we get error:
        //Required request parameter 'email' for method parameter type String is not present
            System.out.println(emailServ);
            System.out.println(passwordServ);
        System.out.println(headerServ);
        return "postBook";
    }
    @DeleteMapping()
    public String deleteBook() {
        System.out.println("---------------Delete BOOK-----------------");
        return "deleteBook";
    }
}

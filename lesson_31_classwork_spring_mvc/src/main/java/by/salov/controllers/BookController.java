package by.salov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerAdapter;

@Controller
@RequestMapping(value = "/book")
public class BookController  {

    @RequestMapping(value = "/search/", method = RequestMethod.GET)
    public String getBookSearch() {
        System.out.println("---------------SEARCH BOOK GET HOME-----------------");
        return "searchbookhome";
    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getBook() {
        System.out.println("---------------HOME BOOK-----------------");
        return "bookHome";
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String postBook() {
        System.out.println("---------------POST BOOK-----------------");
        return "postBook";
    }
}

package by.anatoly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/cars")
public class CarController {

    private final JdbcTemplate jdbcTemplate;

    public CarController(@Qualifier("jdbcTemplateNumber1") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public ModelAndView createCarByName(
            @RequestParam("name") String name,
            @RequestParam("age") int age,
            ModelAndView modelAndView
    ) {
        //For add car to DB we use method update
        int update = jdbcTemplate.update("insert into cars (name,age) values (?,?)", name, age);
        modelAndView.addObject("update", update);
        modelAndView.setViewName("cars");
        return modelAndView;
    }
}

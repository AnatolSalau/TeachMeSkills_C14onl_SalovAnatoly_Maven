package by.anatoly.controllers;

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

    public CarController(@Qualifier(value = "jdbc1") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @GetMapping
    public ModelAndView addCar(
        ModelAndView modelAndView,
        @RequestParam("name") String name,
        @RequestParam("age") int age
    ) {
        int update = jdbcTemplate.update("insert into cars (name,age) values (?,?)", name, age);
        System.out.println(update);
        modelAndView.setViewName("cars");
        modelAndView.addObject("update",String.valueOf(update));
        return modelAndView;
    }
}

package by.anatoly.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/cars")
public class CarController {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper rowMapper;

    public CarController(@Qualifier(value = "jdbc1") JdbcTemplate jdbcTemplate,
                         @Qualifier(value = "carRowMapper") RowMapper rowMapper
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }
    //Add car to database
    @PostMapping
    public ModelAndView addCar(
        ModelAndView modelAndView,
        @RequestParam("name") String name,
        @RequestParam("age") int age
    ) {
        int update = jdbcTemplate.update("insert into cars (name,age) values (?,?)", name, age);

        modelAndView.setViewName("cars");
        modelAndView.addObject("update",String.valueOf(update));
        return modelAndView;
    }
    //Update age
    @PutMapping
    public ModelAndView updateCar(
            ModelAndView modelAndView,
            @RequestParam("name") String name,
            @RequestParam("age") int age
    ) {
        int update = jdbcTemplate.update("update cars set age = ? where name = ?", age, name);
        modelAndView.setViewName("cars");
        modelAndView.addObject("update",String.valueOf(update));
        return modelAndView;
    }
    //Metod for get cars
    @GetMapping
    public void getCar(@RequestParam("name") String name,
                       @RequestParam("age") int age
    ) {
        //Get all cars from DB
        List query = jdbcTemplate.query("select * from cars", rowMapper);
        System.out.println(query);
    }
}

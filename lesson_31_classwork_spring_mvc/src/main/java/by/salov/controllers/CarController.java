package by.salov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/car")
public class CarController {
/*

*    @RequestScope - любой объект либо сервис будет создаваться для каждого реквеста
    @SessionScope - любой объект либо сервис будет создаваться для конкретной сессии
    @ApplicationScope - будет доступен для сессии всего приложения

* */
/*    @GetMapping()
    String getCar(Model model) {
        // для модели по умолчанию стоит @RequestScope - область жизни для реквеста
        model.addAttribute("car", "car1");

        return "getcar";
    }*/
    @GetMapping()
    public ModelAndView getCar(ModelAndView modelAndView,
                               @CookieValue(value = "JSESSIONID",required = false) String jsessionid
                       ) {
        // для модели по умолчанию стоит @RequestScope - область жизни для реквеста

        return modelAndView;
    }
    //We can get path variables
    @GetMapping(path = "/{type}/{color}")
    public ModelAndView getTypeCar(ModelAndView modelAndView,
                                   @PathVariable(name = "type")String typeCar,
                                   @PathVariable(name = "color")String typeColor
        ) {
        modelAndView.setViewName("cartypecolor");
        modelAndView.addObject("type", typeCar);
        modelAndView.addObject("color",typeColor);
        return modelAndView;
    }
}

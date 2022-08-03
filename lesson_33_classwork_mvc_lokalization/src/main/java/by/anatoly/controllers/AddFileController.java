package by.anatoly.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/addfile")
public class AddFileController {
    @GetMapping
    public ModelAndView getFile(
            ModelAndView modelAndView
    ) {
        modelAndView.setViewName("addfile");
        return modelAndView;
    }
    @PostMapping
    public ModelAndView postFile(
            ModelAndView modelAndView,
            @RequestParam(value = "file") MultipartFile multipartFile
    ) throws IOException {
        modelAndView.setViewName("addfile");
        byte[] bytes = multipartFile.getInputStream().readAllBytes();
        System.out.println(new String(bytes));
        return modelAndView;
    }
}

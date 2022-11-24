package by.salov.lesson29_spring_aop;

import by.salov.lesson29_spring_aop.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lesson29SpringAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lesson29SpringAopApplication.class, args);
    }

}

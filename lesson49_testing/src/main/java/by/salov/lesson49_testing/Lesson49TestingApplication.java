package by.salov.lesson49_testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "by.salov.lesson49_testing.client")
public class Lesson49TestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lesson49TestingApplication.class, args);
    }

}

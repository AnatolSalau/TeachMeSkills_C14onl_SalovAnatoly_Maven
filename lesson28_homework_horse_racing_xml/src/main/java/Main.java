import entity.Horse;
import entity.Pair;
import entity.Rider;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Horse horse1 = applicationContext.getBean(Horse.class);
        Rider rider1 = applicationContext.getBean(Rider.class);
        System.out.println(horse1);
        System.out.println(rider1);
    }
}

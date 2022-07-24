import model.DataBase;
import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.LoginService;
import service.LoginServiceImpl;
import service.UserService;
import service.UserServiceDBImpl;

public class Main {
    public static void main(String[] args) {

        User user1 = new User("user1","password1",20,true);
        User user2 = new User("user2","password2",20,true);
        User user3 = new User("user3","password3",20,true);
        User user4 = new User("user4","password4",20,true);


        //We must get context of Spring by ApplicationContext ()
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        //Turn on destroy methods
        applicationContext.registerShutdownHook();
        LoginService loginService = applicationContext.getBean(LoginService.class);
        loginService.save(user1);
        loginService.save(user2);
        loginService.save(user3);
        loginService.save(user4);
    }
}

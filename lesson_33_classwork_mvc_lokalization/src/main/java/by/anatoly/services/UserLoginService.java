package by.anatoly.services;

import by.anatoly.exceptions.FirstException;
import by.anatoly.exceptions.SecondException;
import by.anatoly.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

// @Component("userLoginService") - annotation for services
@Component
public class UserLoginService {
    public void checkLogin(User user) throws FirstException,SecondException{
        if (user.getPassword().equals("1111")) {
            throw new FirstException("User password is 1111");
        }
        if (user.getPassword().equals("2222")) {
            throw new SecondException("User password is 1111");
        }
    }
}

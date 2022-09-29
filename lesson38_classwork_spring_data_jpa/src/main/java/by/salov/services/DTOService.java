package by.salov.services;

import by.salov.entity.User;
import by.salov.entity.UserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Component
public class DTOService {
    String pattern = "MM-dd-yyyy";
    DateFormat df = new SimpleDateFormat(pattern);

    public UserDTO getUserDTO (User user) {
        return new UserDTO(user.getName(), user.getAge(),  df.format(user.getBirthday()));
    }

}

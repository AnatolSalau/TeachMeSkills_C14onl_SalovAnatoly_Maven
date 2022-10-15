package by.salov.lesson49_testing.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@Getter
@Setter
/*Turn on logger by Lombok annotation*/
@Slf4j
public class UserService {


    private List<User> userList;

    public void printUsers() {
        log.error("SLFJLombok");
        userList.forEach(user -> System.out.println(user.getName()));
    }
}

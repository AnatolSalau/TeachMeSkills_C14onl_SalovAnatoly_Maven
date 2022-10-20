package by.salov.lesson49_testing.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;

/*Enable mvc testing to make controller GetUserController  testing*/
@WebMvcTest(GetUserController.class)
class GetUserControllerTest {

    @Test
    void getUserByID() {
    }

    @Test
    void user() {
    }
}
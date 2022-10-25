package by.salov.lesson49_testing.controllers;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.services.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserExternalResourceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void  testAddUser() throws Exception {
        //given
        User userRequest = User.builder()
                .login("First").password("Password").build();
        User userResponse = User.builder()
                .id(1L).login("First").password("Password").build();


        //when
        /*Create request by MockMVC*/
        String jsonUserRequest = objectMapper.writeValueAsString(userRequest);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/test")
                .contentType("application/json")
                .content(jsonUserRequest)
        ).andDo(MockMvcResultHandlers.print());

        //then
        //Compare results
        resultActions
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.id", CoreMatchers.is(1)));
    }
}

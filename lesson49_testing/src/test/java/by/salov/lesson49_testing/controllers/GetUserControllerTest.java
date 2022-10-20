package by.salov.lesson49_testing.controllers;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.exception.CantUpdateUserExeption;
import by.salov.lesson49_testing.exception.UserAllreadyExistExeption;
import by.salov.lesson49_testing.exception.UserIDMustBeNull;
import by.salov.lesson49_testing.exception.UserNotExist;
import by.salov.lesson49_testing.services.UserService;
import by.salov.lesson49_testing.services.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

/*Enable mvc testing to make controller GetUserController  testing*/
@WebMvcTest(GetUserController.class)
class GetUserControllerTest {

    /*Autovired bean UserServiceImpl*/
    @MockBean
    private UserServiceImpl serviceImpl;

    /*Create class from Mockito, that will create request instead of user*/
    @Autowired
    private MockMvc mockMvc;

    /*ObjectMapper for generating JSON*/
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void testGetUserByID() {

    }

    @Test
    void testAddUser() throws Exception {
        //given
        User userRequest = User.builder()
                .login("First").password("Password").build();
        User userResponse = User.builder()
                .id(1L).login("First").password("Password").build();

            Mockito.when(serviceImpl.saveUser(userRequest)).thenReturn(userResponse);

        //when
        /*Create request by MockMVC*/
        String jsonUserRequest = objectMapper.writeValueAsString(userRequest);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                    .post("/add")
                    .contentType("application/json")
                    .content(jsonUserRequest)
            ).andDo(MockMvcResultHandlers.print());

        //then
            resultActions
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andExpect(MockMvcResultMatchers.jsonPath(
                            "$.id", CoreMatchers.is("1")));
    }
}
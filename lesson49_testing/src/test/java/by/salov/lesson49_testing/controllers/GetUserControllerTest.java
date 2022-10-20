package by.salov.lesson49_testing.controllers;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.exception.UserAllreadyExistExeption;
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
    void testAddUser()  {
        //given
        User userRequest = User.builder()
                .login("First").password("Password").build();
        User userResponse = User.builder()
                .id(1L).login("First").password("Password").build();

        try {
            Mockito.when(serviceImpl.saveUser(userRequest)).thenReturn(userResponse);
        } catch (UserNotExist e) {
            e.printStackTrace();
        } catch (UserAllreadyExistExeption e) {
            e.printStackTrace();
        }
        //when
        /*Create request by MockMVC*/
        ResultActions resultActions = null;
        try {
            resultActions = mockMvc.perform(MockMvcRequestBuilders
                    .post("/")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(userRequest))
            ).andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //then
        try {
            resultActions
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath(
                            "$.id", CoreMatchers.is(1L)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
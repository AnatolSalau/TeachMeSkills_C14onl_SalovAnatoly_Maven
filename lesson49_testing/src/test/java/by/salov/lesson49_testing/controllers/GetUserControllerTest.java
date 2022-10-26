package by.salov.lesson49_testing.controllers;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.services.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
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
import org.springframework.web.servlet.ModelAndView;

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
    void testGetUserByID() throws Exception {
        //given
        User userResponse = User.builder()
                .id(2L).login("Login").password("Password").build();

        Mockito.when(serviceImpl.getUserById(2L)).thenReturn(userResponse);

        //when
        ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.get("/{id}",2)
                )
                .andDo(MockMvcResultHandlers.print());
        //then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.id",CoreMatchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.login",CoreMatchers.is("Login")))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.password",CoreMatchers.is("Password")));
        //Also we can get object
        //get string
        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        //get object from string
        User user = objectMapper.readValue(contentAsString, User.class);

        Assertions.assertEquals(user.getId(),2L);

    }

    @Test
    void testAddUser() throws Exception {
        //given
        User userRequest = User.builder()
                .login("First").password("Password").build();
        User userResponse = User.builder()
                .id(1L).login("First").password("Password").build();

        /*UserServiceImpl должен вернуть userResponse а возвращает null - прикрепил скриншот*/
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
        //Compare results
            resultActions
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andExpect(MockMvcResultMatchers.jsonPath(
                            "$.id", CoreMatchers.is(1)));
    }

    @Test
    void getNotExistUserResponseEntityByID() throws Exception {
        //given
        //Return null like we get null from db
        Mockito.when(serviceImpl.getUserById(2L)).thenReturn(null);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/get/{id}","2")
        ).andDo(MockMvcResultHandlers.print());

        //then
        //Compare requests
        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    //test ModelAndView
    @Test
    void testGetTestTemplate() throws Exception {
        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/test")).andDo(MockMvcResultHandlers.print());
        //given
        ModelAndView modelAndView = resultActions.andReturn().getModelAndView();
        String viewName = modelAndView.getViewName();
        System.out.println(viewName);
        //then
        Assertions.assertEquals(viewName,"test.html");
    }
}
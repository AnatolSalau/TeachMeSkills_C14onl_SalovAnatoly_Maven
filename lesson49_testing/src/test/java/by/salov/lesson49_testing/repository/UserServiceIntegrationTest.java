package by.salov.lesson49_testing.repository;

import by.salov.lesson49_testing.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Integrations tests by @SpringBootTest
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserServiceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {

    }

    @Test
    public void testGetByID() throws Exception {
        //given
        Long id = 5L;
        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .get("/{id}", id))
                .andDo(MockMvcResultHandlers.print());
        //then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.id",CoreMatchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.login",CoreMatchers.is("login5")));
    }

    @Test
    void testSaveUser() throws Exception {
        //given
        User userRequest = User.builder()
                .login("testlogin")
                .password("testpassword")
                .build();
        //when
        //Simulate REST request by mockMVC
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userRequest))
        ).andDo(MockMvcResultHandlers.print());
        //then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.login", CoreMatchers.is("testlogin")))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.password", CoreMatchers.is("testpassword")));
    }

    @Test
    void testSaveUserWithID() throws Exception {
        //given
        User userRequest = User.builder()
                .id(1L)
                .login("login1")
                .password("testpassword")
                .build();
        //when
        //Simulate REST request by mockMVC
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userRequest))
        ).andDo(MockMvcResultHandlers.print());
        //then
        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}

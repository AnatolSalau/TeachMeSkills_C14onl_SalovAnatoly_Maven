package by.salov.lesson49_testing.repository;

import by.salov.lesson49_testing.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class UserRepositoryIntegrationTest {
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
        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .get("/{id}", "5"))
                .andDo(MockMvcResultHandlers.print());
        //then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}

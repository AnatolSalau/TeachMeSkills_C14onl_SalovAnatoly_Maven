package by.salov.lesson49_testing.controllers;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.exception.CantUpdateUserExeption;
import by.salov.lesson49_testing.exception.UserIDMustBeNull;
import by.salov.lesson49_testing.exception.UserNotValidExeption;
import by.salov.lesson49_testing.services.impl.UserServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Integration test by Testcontainer (Run Docker container with Postgres)
 * DB runs in container
 * Before start TestContainer tests we can run Docker on PC
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class GetUserControllerTestContainerIntegrationTest {
        @Autowired
        private MockMvc mockMvc;

        /*Create PostgreSQL container*/
        @Container
        public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>(
                "postgres:13.8")
                .withDatabaseName("test")
                .withUsername("admin")
                .withPassword("admin");

        /*Initialize properties from container*/
        /*We must delete*/
        @DynamicPropertySource
        public static void initProperties(DynamicPropertyRegistry registry) {
                registry.add("spring.datasource.url",() -> {
                        return postgreSQLContainer.getJdbcUrl();
                });
                registry.add("spring.datasource.driver-class-name",() -> {
                        return postgreSQLContainer.getDriverClassName();
                });
                registry.add("spring.datasource.username",() -> {
                        return postgreSQLContainer.getUsername();
                });
                registry.add("spring.datasource.password",() -> {
                        return postgreSQLContainer.getPassword();
                });
        }

        @Autowired
        private UserServiceImpl userServiceImpl;
        /*We can add user by Java or create SQL script in data.sql, schema.sql files*/
        @BeforeEach
        public void beforeEach() throws UserIDMustBeNull, UserNotValidExeption, CantUpdateUserExeption {
                User user1 = User.builder()
                        .login("login1")
                        .password("password1")
                        .build();
                User user2 = User.builder()
                        .login("login1")
                        .password("password1")
                        .build();
/*                userServiceImpl.deleteAll();
                userServiceImpl.saveUser(user1);
                userServiceImpl.saveUser(user2);*/
        }

        @Test
        public void testGetByID() throws Exception {
                //given
                int id = 7;
                //when
                ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                                .get("/{id}", id))
                        .andDo(MockMvcResultHandlers.print());
                //then
                resultActions
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath(
                                "$.id", CoreMatchers.is(id)))
                        .andExpect(MockMvcResultMatchers.jsonPath(
                                "$.login",CoreMatchers.is("login7")));
        }
}

package by.salov.lesson49_testing.controllers;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.services.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
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

    //Create external resource by WireMock and register like extension in JUnit5
    //put port from external resource where feign client get data
    @RegisterExtension
    static WireMockExtension WIREMOCKEX = WireMockExtension.newInstance()
            .options(WireMockConfiguration.options().port(8099))
            .build();
    //
    @Test
    void  testAddUser() throws Exception {
        //given
        User userRequestExternalResource = User.builder()
                .login("ExternalLogin1").password("ExternalPassword1").build();
        User userResponseExternalResource = User.builder()
                .id(1L).login("ExternalLogin1").password("ExternalPassword1").build();

        String userResponseString = objectMapper.writeValueAsString(userResponseExternalResource);

        //knocking wiremock by the url where feign client get data
        //@FeignClient(name = "UserFeignClient", url = "http://127.0.0.1:8099", path = "/user")
        //public interface UserFeignClient {}
        WIREMOCKEX.stubFor(WireMock.get("/user")
                .willReturn(WireMock
                        .ok(userResponseString)
                        .withHeader("Content-Type", "application/json")
                )
        );

        //when
        /*Create request by MockMVC*/
        String jsonUserRequest = objectMapper.writeValueAsString(userRequestExternalResource);
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
                        "$.id", CoreMatchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.login", CoreMatchers.is("ExternalLogin1")))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.password", CoreMatchers.is("ExternalPassword1")))
        ;
    }
}

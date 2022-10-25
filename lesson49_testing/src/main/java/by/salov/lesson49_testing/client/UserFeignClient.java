package by.salov.lesson49_testing.client;

import by.salov.lesson49_testing.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Create client that get data from http://127.0.0.1:8099/user
 */
@FeignClient(name = "UserFeignClient", url = "http://127.0.0.1:8099", path = "/user")
public interface UserFeignClient {

    @GetMapping()
    User getUser();
}

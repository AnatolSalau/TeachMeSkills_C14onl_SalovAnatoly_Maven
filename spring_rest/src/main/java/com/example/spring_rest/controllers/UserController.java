package com.example.spring_rest.controllers;

import com.example.spring_rest.dto.ErrorDTO;
import com.example.spring_rest.dto.Gender;
import com.example.spring_rest.dto.SearchDTO;
import com.example.spring_rest.dto.User;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/api/user")
/*@Tag(name = "User controller",
        description = "User CRUD operations" )*/
public class UserController {

    /**
     * Controller with HATEOAS
     * @return - list of users
     */
    @GetMapping
    @Tag(name = "get All", description = "Get all users")
    public ResponseEntity<List<User>> getAllUsers() {


        User user1 = new User(1L,"user",
                "1111", Gender.MALE, new Date());
        User user2 = new User(2L,"user",
                "1111", Gender.MALE, new Date());

        //HATEOAS
        Link linkUser1 = WebMvcLinkBuilder
                .linkTo(
                        WebMvcLinkBuilder
                                .methodOn(UserController.class)
                                .getUserById(1L)

                )
                .withRel("get user with 1L ID");
        Link linkUser2 = WebMvcLinkBuilder
                .linkTo(
                        WebMvcLinkBuilder
                                .methodOn(UserController.class)
                                .getUserById(2L)
                )
                .withSelfRel();
        user1.add(linkUser1);
        user2.add(linkUser2);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    @Tag(name = "get user", description = "Get user by ID")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
        System.out.println("Get by id : " + userId);
        User user = new User(1L,"user",
                "1111", Gender.MALE, new Date());
        user.setId(userId);
        //Cache control
        return ResponseEntity
                .status(200)
                .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                .body(user);
    }

    @GetMapping("/error/{userId}")
    @Tag(name = "get user or error", description = "Get error if user ID is 0")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success operation",
                    headers = {
                        @Header(name = "testHeader", required = true,
                                description = "just for test")
                    },
                    content = {
                        @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Something broken",
                    headers = {
                            @Header(name = "testHeader", required = true,
                                    description = "exception")
                    },
                    content = {
                            @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = ErrorDTO.class))
                    }
            )
    })
    public ResponseEntity<?> getErrorById(@PathVariable("userId") Long userId) {

        User user = new User(1L,"user",
                "1111", Gender.MALE, new Date());
        user.setId(userId);
        if(userId == 0L) {
            System.out.println("Get error by id : " + userId);
            //Return 400 status code to client and ErrorDTO between User
            return ResponseEntity
                    .status(400)
                    .header("testHeader", "error")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ErrorDTO("ErrorDTO from exception handler"));
        }
        return ResponseEntity
                .status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .header("testHeader", "user was get")
                .body(user);
    }


    @PostMapping
    @Tag(name = "Post for user", description = "Save information about new user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        System.out.println(user);
        //Add custom header
        return ResponseEntity
                .status(201)
                .header("testHeader", "user was created")
                .body(user);
    }
    //search user by post
    @PostMapping(path = "/search")
    @Tag(name = "search user", description = "search user by searchDTO")
    public ResponseEntity<List<User>> searchUser(@RequestBody SearchDTO searchDTO) {
        System.out.println("SearchDTO : " + searchDTO);
        User user1 = new User(searchDTO.getMinId(), searchDTO.getLogin(),
                "1111", searchDTO.getGender(), new Date());
        User user2 = new User(searchDTO.getMaxId(), searchDTO.getLogin(),
                "2222", searchDTO.getGender(), new Date());
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        //Add custom header
        return ResponseEntity
                .status(200)
                .body(users);
    }

    @PutMapping
    @Tag(name = "update user", description = "update whole user")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        System.out.println("User : " + user + " was update");
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    @Tag(name = "delete user", description = "delete user by id")
    public ResponseEntity<User> delete(@PathVariable("userId") Long userId) {
        System.out.println("Delete user with id : " + userId);
        User user = new User(1L,"user",
                "1111", Gender.MALE, new Date());
        user.setId(userId);
        return ResponseEntity.ok(user);
    }
}

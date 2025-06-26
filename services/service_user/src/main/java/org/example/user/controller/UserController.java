package org.example.user.controller;

import org.example.response.Response;
import org.example.user.properties.UserProperties;
import org.example.user.service.UserService;
import org.example.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/db/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserProperties userProperties;



    @GetMapping("/config")
    public String config() {
        return "Max Password Length: " + userProperties.getPasswordLengthMax();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }


    // ------------------------------------------------------------
    @GetMapping("/getuserbyid")
    public Response<?> getUserByUsernameOrId(@RequestParam Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return Response.newError(HttpStatus.NOT_FOUND.value(), "User not found");
        }
        return Response.newSuccess(user, "User retrieved successfully");
    }
    @GetMapping("/getuserbyusername")
    public Response<?> getUserByUsername(@RequestParam String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return Response.newError(HttpStatus.NOT_FOUND.value(), "User not found");
        }
        return Response.newSuccess(user, "User retrieved successfully");
    }
    @GetMapping("/getallusers")
    public Response<?> getAllUsers() {
        return Response.newSuccess(userService.getAllUsers(), "All users retrieved successfully");
    }
    @PostMapping("/adduser")
    public Response<?> addUser(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return Response.newError(HttpStatus.BAD_REQUEST.value(), "Username and password are required");
        }
        User createdUser = userService.addUser(user);
        return Response.newSuccess(createdUser, "User created successfully");
    }

    @DeleteMapping("/deleteuser")
    public Response<?> deleteUserById(@RequestParam Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return Response.newError(HttpStatus.NOT_FOUND.value(), "User not found");
        }
        userService.deleteUserById(id);
        return Response.newSuccess(null, "User deleted successfully");
    }
}

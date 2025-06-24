package org.example.user.controller;


import org.example.user.properties.UserProperties;
import org.example.user.service.UserService;
import org.example.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}

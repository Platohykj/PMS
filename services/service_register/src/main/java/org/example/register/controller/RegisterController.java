package org.example.register.controller;


import org.example.register.request.LoginRequest;
import org.example.register.service.RegisterService;
import org.example.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @GetMapping("/auth/register/{id}")
    public User getUserFromRemote(@PathVariable("id") Long id){
        User user = registerService.getUserFromRemote(id);
        return user;
    }

    @PostMapping("/auth/register")
    public Response<?> registerUser(LoginRequest loginRequest) {
        return registerService.registerUser(loginRequest);
    }
}

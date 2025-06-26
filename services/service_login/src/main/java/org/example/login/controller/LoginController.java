package org.example.login.controller;


import org.example.login.request.LoginRequest;
import org.example.login.service.LoginService;
import org.example.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @PostMapping("/auth/login")
    public Response<?> login(@RequestBody LoginRequest loginRequest) {
        return Response.newSuccess(loginService.login(loginRequest), "Login successful");
    }


}



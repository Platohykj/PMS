package org.example.login.controller;

import org.example.login.properties.LoginProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginProperties loginProperties;

}

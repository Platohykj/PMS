package org.example.login.service.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.employee.model.Employee;
import org.example.login.feign.UserFeignClient;
import org.example.login.request.LoginRequest;
import org.example.login.service.LoginService;
import org.example.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserFeignClient userFeignClient;

    @Override
    public User login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        return userFeignClient.login(username, password);
    }
}

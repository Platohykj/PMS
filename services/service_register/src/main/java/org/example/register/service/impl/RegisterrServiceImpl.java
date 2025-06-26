package org.example.register.service.impl;


import org.example.register.feign.UserFeignClient;
import org.example.register.request.LoginRequest;
import org.example.register.service.RegisterService;
import org.example.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.stereotype.Service;

@Service
public class RegisterrServiceImpl implements RegisterService {

    @Autowired
    UserFeignClient userFeignClient;

    @Override
    public User getUserFromRemote(Long userId) {
        User user = userFeignClient.getUserById(userId);
        return user;
    }

    @Override
    public Response<?> registerUser(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        return userFeignClient.register(username, password);
    }

}

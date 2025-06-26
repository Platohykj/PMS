package org.example.register.service;


import org.example.register.request.LoginRequest;
import org.example.user.model.User;
import org.springframework.cloud.client.loadbalancer.Response;

public interface RegisterService {
    User getUserFromRemote(Long userId);

    Response<?> registerUser(LoginRequest loginRequest);
}

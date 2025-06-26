package org.example.register.service.impl;


import org.example.register.feign.UserFeignClient;
import org.example.register.service.RegisterService;
import org.example.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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

}

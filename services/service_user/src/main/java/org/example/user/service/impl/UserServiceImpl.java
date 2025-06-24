package org.example.user.service.impl;


import org.example.user.service.UserService;
import org.example.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(Long id) {
        User user = new User();
        user.setId(id);
        user.setUsername("sgt");
        return user;
    }
}

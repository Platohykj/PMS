package org.example.register.service;


import org.example.user.model.User;

public interface RegisterService {
    User getUserFromRemote(Long userId);
}

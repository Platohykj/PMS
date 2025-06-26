package org.example.user.service;


import org.example.user.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    User getUserByUsername(String username);

    User addUser(User user);

    void deleteUserById(Long id);

    List<User> getAllUsers();
}

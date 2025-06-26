package org.example.login.service;


import org.example.login.request.LoginRequest;
import org.example.user.model.User;

import java.io.IOException;
import java.util.List;

public interface LoginService {

    User login(LoginRequest loginRequest);
}

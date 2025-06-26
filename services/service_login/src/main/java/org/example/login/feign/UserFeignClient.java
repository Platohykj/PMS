package org.example.login.feign;

import org.example.employee.model.Employee;
import org.example.response.Response;
import org.example.user.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-user")
public interface UserFeignClient {
    @PostMapping("/api/db/user/login")
    public User login(@RequestParam String username, @RequestParam String password);
}

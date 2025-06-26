package org.example.register.feign;

import org.example.user.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-user")
public interface UserFeignClient {
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable("id") Long id);

}

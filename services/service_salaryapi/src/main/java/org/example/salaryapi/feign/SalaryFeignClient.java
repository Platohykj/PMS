package org.example.salaryapi.feign;


import org.example.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "service-salary")
public interface SalaryFeignClient {
    @GetMapping("/api/db/salary/getallsalaries")
    public Response<?> getAllSalaries();
}

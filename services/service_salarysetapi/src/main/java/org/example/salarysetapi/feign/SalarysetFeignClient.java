package org.example.salarysetapi.feign;


import org.example.response.Response;
import org.example.salary.model.Salary;
import org.example.salarySet.model.SalarySet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-salaryset")
public interface SalarysetFeignClient {
    @GetMapping("/api/db/salaryset/getallsalaryset")
    public Response<?> getAllSalarySet();

    @PostMapping("/api/db/salaryset/addsalaryset")
    public Response<?> addSalarySet(@RequestBody SalarySet salarySet);

    @PutMapping("/api/db/salaryset/updatesalaryset")
    public Response<?> updateSalarySet(@RequestBody SalarySet salarySet);

    @DeleteMapping("/api/db/salaryset/deletesalaryset")
    public Response<?> deleteSalarySet(@RequestParam Long id);
}

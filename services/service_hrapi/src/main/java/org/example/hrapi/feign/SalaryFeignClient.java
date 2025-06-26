package org.example.hrapi.feign;


import org.example.response.Response;
import org.example.salary.model.Salary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(value = "service-salary")
public interface SalaryFeignClient {
    @GetMapping("/api/db/salary/getsalarybyjobid")
    public Response<?> getSalaryByJobId(@RequestParam String jobId);
    @PutMapping("/api/db/salary/update")
    public Response<?> updateSalary(@RequestBody Salary salary);
    @PostMapping("/api/db/salary/updatebasesalary")
    public Response<?> updateBaseSalary(@RequestParam String jobId, @RequestParam double baseSalary);
}

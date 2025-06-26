package org.example.salary.controller;


import org.example.response.Response;
import org.example.salary.model.Salary;
import org.example.salary.repository.SalaryRepository;
import org.example.salary.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/db/salary")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    @PostMapping("/updatebasesalary")
    public Response<?> updateBaseSalary(@RequestParam String jobId, @RequestParam double baseSalary) {
        Salary salary = salaryService.getSalaryByJobId(jobId).stream().findFirst().orElse(null);
        if (salary != null) {
            salary.setBaseSalary(BigDecimal.valueOf(baseSalary));
            salaryService.updateSalary(salary);
            return Response.newSuccess(salary, "Base salary updated successfully");
        } else {
            return Response.newError(404, "No salary found for the given job ID");
        }
    }

    @PostMapping("/addsalary")
    public Response<?> addSalary(@RequestBody Salary salary) {
        salaryService.addSalary(salary);
        return Response.newSuccess(salary, "Salary added successfully");
    }
    @GetMapping("/getsalarybyjobid")
    public Response<?> getSalaryByJobId(@RequestParam String jobId) {
        List<Salary> salary = salaryService.getSalaryByJobId(jobId);
        if (salary != null) {
            return Response.newSuccess(salary, "Salaries retrieved successfully");
        } else {
            return Response.newError(404, "No salaries found for the given job ID");
        }
    }
    @GetMapping("/getallsalaries")
    public Response<?> getAllSalaries() {
        List<Salary> salaries = salaryService.getAllSalaries();
        if (salaries != null && !salaries.isEmpty()) {
            return Response.newSuccess(salaries, "Salaries retrieved successfully");
        } else {
            return Response.newError(404, "No salaries found");
        }
    }
    @PutMapping("/update")
    public Response<?> updateSalary(@RequestBody Salary salary) {
        salaryService.updateSalary(salary);
        return Response.newSuccess(salary, "Salary updated successfully");
    }
}

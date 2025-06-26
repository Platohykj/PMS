package org.example.salaryapi.controller;


import org.example.response.Response;
import org.example.salaryapi.service.SalaryapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salary")
public class SalaryapiController {
    @Autowired
    private SalaryapiService salaryapiService;

    @GetMapping("/querySelf")
    public Response<?> queryAll() {
        return salaryapiService.queryAll();
    }

}



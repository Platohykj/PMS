package org.example.salarysetapi.controller;


import org.example.response.Response;
import org.example.salarySet.model.SalarySet;
import org.example.salarysetapi.service.SalarysetapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salaryset")
public class SalarysetapiController {

    @Autowired
    SalarysetapiService salarysetapiService;

    @GetMapping("/list")
    public Response<?> list() {
        return salarysetapiService.list();
    }

    @PostMapping("/save")
    public Response<?> save(@RequestBody SalarySet salarySet) {
        return salarysetapiService.saveSalarySet(salarySet);
    }
    @PostMapping("/update")
    public Response<?> update(@RequestBody SalarySet salarySet) {
        return salarysetapiService.updateSalarySet(salarySet);
    }

    @DeleteMapping("/delete/{id}")
    public Response<?> delete(@PathVariable Long id) {
        return salarysetapiService.deleteSalarySet(id);
    }

}



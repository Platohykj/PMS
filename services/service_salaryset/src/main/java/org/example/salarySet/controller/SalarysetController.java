package org.example.salarySet.controller;



import org.example.response.Response;
import org.example.salarySet.model.SalarySet;
import org.example.salarySet.service.SalarysetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/db/salaryset")
public class SalarysetController {

    @Autowired
    private SalarysetService salarysetService;

    @GetMapping("/getallsalaryset")
    public Response<?> getAllSalarySet() {
        return Response.newSuccess(salarysetService.getAllSalarySet(), "Get all salary sets successfully");
    }

    @PostMapping("/addsalaryset")
    public Response<?> addSalarySet(@RequestBody SalarySet salarySet) {
        try {
            salarysetService.addSalarySet(salarySet);
            return Response.newSuccess(null, "Salary set added successfully");
        } catch (Exception e) {
            return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to add salary set: " + e.getMessage());
        }
    }

    @PutMapping("/updatesalaryset")
    public Response<?> updateSalarySet(@RequestBody SalarySet salarySet) {
        try {
            salarysetService.updateSalarySet(salarySet);
            return Response.newSuccess(null, "Salary set updated successfully");
        } catch (Exception e) {
            return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to update salary set: " + e.getMessage());
        }
    }

    @DeleteMapping("/deletesalaryset")
    public Response<?> deleteSalarySet(@RequestParam Long id) {
        try {
            salarysetService.deleteSalarySet(id);
            return Response.newSuccess(null, "Salary set deleted successfully");
        } catch (Exception e) {
            return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to delete salary set: " + e.getMessage());
        }
    }

}

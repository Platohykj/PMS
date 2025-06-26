package org.example.employee.controller;

import org.example.employee.model.Employee;
import org.example.employee.service.EmployeeService;
import org.example.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/db/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getallemployees")
    public Response<?> getAllEmployees() {
        return Response.newSuccess(employeeService.getAllEmployees(), "All employees retrieved successfully");
    }
    @GetMapping("/getemployeebyjobId")
    public Response<?> getEmployeeById(@RequestParam("jobId") String jobId) {
        return Response.newSuccess(employeeService.getEmployeeByjobId(jobId), "Employee retrieved successfully");
    }
    @PostMapping("/addemployee")
    public Response<?> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return Response.newSuccess(null, "Employee added successfully");
    }
    @DeleteMapping("/deleteemployee")
    public Response<?> deleteEmployee(@RequestParam("jobId") String jobId) {
        employeeService.deleteEmployee(jobId);
        return Response.newSuccess(null, "Employee deleted successfully");
    }
    @PutMapping("/updateemployee")
    public Response<?> updateEmployee(@RequestBody Employee employee) {
        String jobId = employee.getJobId();
        employeeService.updateEmployee(employee);
        return Response.newSuccess(null, "Employee updated successfully");
    }
}

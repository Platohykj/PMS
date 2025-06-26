package org.example.employee.controller;

import org.example.employee.model.Employee;
import org.example.employee.service.EmployeeService;
import org.example.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping("/clearsubdepartment")
    public Response<?> clearSubDepartment(@RequestParam("subdepartment") String subdepartment) {
        employeeService.clearSubDepartment(subdepartment);
        return Response.newSuccess(null, "Sub-department cleared successfully");
    }
    @PutMapping("/clearparentdepartments")
    public Response<?> clearParentDepartments(@RequestParam("parentDepartment") String parentDepartment) {
        employeeService.clearParentDepartments(parentDepartment);
        return Response.newSuccess(null, "Parent departments cleared successfully");
    }
    @GetMapping("/getemployeebydepartment")
    public Response<?> getEmployeeByDepartment(@RequestParam("department") String department) {
        return Response.newSuccess(employeeService.getEmployeeByDepartment(department), "Employees retrieved by department successfully");
    }
    @PutMapping("/updatetitleid")
    public Response<?> updateTitleId(@RequestParam("jobId") String jobId, @RequestParam("titleId") Long titleId) {
        employeeService.updateTitleId(jobId, titleId);
        return Response.newSuccess(null, "Title ID updated successfully");
    }

    @PutMapping("/removetitle")
    public Response<?> removeTitle(@RequestParam("jobId") String jobId) {
        employeeService.removeTitle(jobId);
        return Response.newSuccess(null, "Title removed successfully");
    }

    @PostMapping("/updatedepartment")
    public Response<?> updateDepartment(@RequestParam("jobId") String jobId, @RequestParam("department") String department) {
        employeeService.updateDepartment(jobId, department);
        return Response.newSuccess(null, "Department updated successfully");
    }

    @PostMapping("/updatesubdepartment")
    public Response<?> updateSubDepartment(@RequestParam("jobId") String jobId, @RequestParam("subDepartment") String subDepartment) {
        Employee employee = employeeService.getEmployeeByjobId(jobId);
        if (employee == null) {
            return Response.newError(404, "Employee not found");
        }
        employee.setSubDepartment(subDepartment);
        employeeService.updateEmployee(employee);
        return Response.newSuccess(null, "Sub-department updated successfully");
    }
    @GetMapping("/getjobidlistbydepartment")
    public List<String> getJobIdListByDepartment(@RequestParam("department") String department) {
        return employeeService.getjobidByDepartment(department);
    }
}

package org.example.department.controller;

import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.example.department.model.Department;
import org.example.department.service.DepartmentService;
import org.example.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/db/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping("/adddepartment")
    public Response<?> addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
        return Response.newSuccess(null, "Department added successfully");
    }

    @GetMapping("/getDepartmentByName")
    public Response<?> getDepartmentByName(@RequestParam String name) {
        Department department = departmentService.getDepartmentByName(name);
        if (department == null) {
            return Response.newError(HttpStatus.SC_NOT_FOUND, "Department not found");
        }
        return Response.newSuccess(department, "Department retrieved successfully");
    }
    @PutMapping("/updateDepartment")
    public Response<?> updateDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
        return Response.newSuccess(null, "Department updated successfully");
    }
    @DeleteMapping("/deleteDepartment")
    public Response<?> deleteDepartment(@RequestBody Department department) {
        departmentService.deleteDepartment(department);
        return Response.newSuccess(null, "Department deleted successfully");
    }
    @GetMapping("/getalldepartments")
    public Response<?> getAllDepartments() {
        return Response.newSuccess(departmentService.getAllDepartments(), "All departments retrieved successfully");
    }

    @DeleteMapping("/deleteParentDepartment")
    public Response<?> deleteParentDepartment(@RequestParam String parentDepartmentName) {
        departmentService.deleteParentDepartment(parentDepartmentName);
        return Response.newSuccess(null, "Parent department deleted successfully");
    }

    @GetMapping("/getallparentDepartment")
    public Response<?> getAllParentDepartments() {
        return Response.newSuccess(departmentService.getAllParentDepartments(), "All parent departments retrieved successfully");
    }

    @GetMapping("/getSubDepartmentsByParentName")
    public Response<?> getSubDepartmentsByParentName(@RequestParam String parentDepartmentName) {
        return Response.newSuccess(departmentService.getSubDepartmentsByParentName(parentDepartmentName), "Sub-departments retrieved successfully");
    }
}

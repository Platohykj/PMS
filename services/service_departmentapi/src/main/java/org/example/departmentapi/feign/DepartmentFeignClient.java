package org.example.departmentapi.feign;

import org.example.department.model.Department;
import org.example.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-department")
public interface DepartmentFeignClient {
    @GetMapping("/api/db/department/getalldepartments")
    public Response<?> getAllDepartments();

    @PostMapping("/api/db/department/adddepartment")
    public Response<?> addDepartment(@RequestBody Department department);

    @GetMapping("/api/db/department/getDepartmentByName")
    public Response<?> getDepartmentByName(@RequestParam String name);

    @PutMapping("/api/db/department/updateDepartment")
    public Response<?> updateDepartment(@RequestBody Department department);

    @DeleteMapping("/api/db/department/deleteDepartment")
    public Response<?> deleteDepartment(@RequestBody Department department);

    @DeleteMapping("/api/db/department/deleteParentDepartment")
    public Response<?> deleteParentDepartment(@RequestParam String parentDepartmentName);

    @GetMapping("/api/db/department/getallparentDepartment")
    public Response<?> getAllParentDepartments();

    @GetMapping("/api/db/department/getSubDepartmentsByParentName")
    public Response<?> getSubDepartmentsByParentName(@RequestParam String parentDepartmentName);
}

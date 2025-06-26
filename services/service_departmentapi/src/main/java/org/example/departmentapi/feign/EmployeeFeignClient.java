package org.example.departmentapi.feign;

import org.example.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-employee")
public interface EmployeeFeignClient {
    @PutMapping("/api/db/employee/clearsubdepartment")
    public Response<?> clearSubDepartment(@RequestParam("subdepartment") String subdepartment);

    @PutMapping("/api/db/employee/clearparentdepartments")
    public Response<?> clearParentDepartments(@RequestParam("parentDepartment") String parentDepartment);
}

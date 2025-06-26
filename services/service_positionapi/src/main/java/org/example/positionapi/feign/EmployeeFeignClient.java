package org.example.positionapi.feign;

import org.example.employee.model.Employee;
import org.example.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-employee")
public interface EmployeeFeignClient {
    @GetMapping("/api/db/employee/getemployeebydepartment")
    public Response<?> getEmployeeByDepartment(@RequestParam("department") String department);

    @GetMapping("/api/db/employee/getemployeebyjobId")
    public Response<?> getEmployeeById(@RequestParam("jobId") String jobId);

    @PutMapping("/api/db/employee/updateemployee")
    public Response<?> updateEmployee(@RequestBody Employee employee);
}

package org.example.hrapi.feign;

import org.example.employee.model.Employee;
import org.example.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-employee")
public interface EmployeeFeignClient {
    @GetMapping("/api/db/employee/getemployeebydepartment")
    public Response<?> getEmployeeByDepartment(@RequestParam("department") String department);

    @GetMapping("/api/db/employee/getemployeebyjobId")
    public Response<?> getEmployeeById(@RequestParam("jobId") String jobId);

    @PutMapping("/api/db/employee/updateemployee")
    public Response<?> updateEmployee(@RequestBody Employee employee);

    @PutMapping("/api/db/employee/updatetitleid")
    public Response<?> updateTitleId(@RequestParam("jobId") String jobId, @RequestParam("titleId") Long titleId);

    @PutMapping("/api/db/employee/removetitle")
    public Response<?> removeTitle(@RequestParam("jobId") String jobId);

    @PostMapping("/api/db/employee/updatedepartment")
    public Response<?> updateDepartment(@RequestParam("jobId") String jobId, @RequestParam("department") String department);

    @PostMapping("/api/db/employee/updatesubdepartment")
    public Response<?> updateSubDepartment(@RequestParam("jobId") String jobId, @RequestParam("subDepartment") String subDepartment);

    @GetMapping("/api/db/employee/getjobidlistbydepartment")
    public List<String> getJobIdListByDepartment(@RequestParam("department") String department);
}

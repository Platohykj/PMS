package org.example.titleapi.controller;



import org.example.response.Response;
import org.example.title.model.Title;
import org.example.titleapi.request.EmployleesRequest;
import org.example.titleapi.request.RemoveRequest;
import org.example.titleapi.request.UpdateEmployeeRequest;
import org.example.titleapi.response.EmployeeResponse;
import org.example.titleapi.service.TitleapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/title")
public class TitleapiController {
    @Autowired
    private TitleapiService titleapiService;

    @GetMapping("/list")
    public Response<?> getTitleList() {
        return titleapiService.getTitleList();
    }

    @GetMapping("/names")
    public Response<?> getTitleNames() {
        return Response.newSuccess(titleapiService.getTitleNames(),"Successfully retrieved all title names."
        );
    }

    @PostMapping("/update")
    public Response<?> updateTitle(@RequestBody Title title) {
        return titleapiService.updateTitle(title);
    }

    @PostMapping("/save")
    public Response<?> saveTitle(@RequestBody Title title) {
        return titleapiService.saveTitle(title);
    }

    @PostMapping("/delete/{id}")
    public Response<?> deleteTitle(@PathVariable("id") Long id) {
        // Assuming there's a delete method in the service
        titleapiService.deleteTitle(id);
        return Response.newSuccess(null, "Successfully deleted title with ID: " + id);
    }

    @GetMapping("/employees")
    public Response<?> getEmployeesByTitle(@RequestBody EmployleesRequest employleesRequest) {
        // Assuming there's a method in the service to get employees by title
        List<EmployeeResponse> employees = titleapiService.getEmployeesByDepartment(employleesRequest);
        return Response.newSuccess(employees, "Successfully retrieved employees");
    }

    @PostMapping("/update-employees")
    public Response<?> updateEmployees(@RequestBody UpdateEmployeeRequest updateEmployeeRequest) {
        // Assuming there's a method in the service to update employees by title
        titleapiService.updateEmployeePosition(updateEmployeeRequest);
        return Response.newSuccess(null, "Successfully updated employees");
    }

    @PostMapping("/remove-employee-title")
    public Response<?> removeEmployeeTitle(@RequestBody RemoveRequest removeRequest) {
        // Assuming there's a method in the service to remove employee title
        titleapiService.removeEmployeeTitle(removeRequest);
        return Response.newSuccess(null, "Successfully removed employee title");
    }


}

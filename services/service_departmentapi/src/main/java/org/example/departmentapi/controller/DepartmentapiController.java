package org.example.departmentapi.controller;



import org.example.departmentapi.request.*;
import org.example.departmentapi.service.DepartmentapiService;
import org.example.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentapiController {

    @Autowired
    DepartmentapiService departmentapiService;

    @GetMapping("/map")
    public Response<?> getDepartmentMap() {
        return Response.newSuccess(departmentapiService.getDepartmentMap(), "Successfully retrieved department map.");
    }

    @PostMapping("/add-parent")
    public Response<?> addParentDepartment(@RequestBody AddParentRequest addParentRequest) {
        departmentapiService.addParentDepartment(addParentRequest);
        return Response.newSuccess(null, "Successfully added parent department.");
    }

    @PostMapping("/add-sub")
    public Response<?> addSubDepartment(@RequestBody AddSubRequest addSubRequest) {
        departmentapiService.addSubDepartment(addSubRequest);
        return Response.newSuccess(null, "Successfully added sub-department.");
    }

    @PutMapping("/rename-parent")
    public Response<?> renameParentDepartment(@RequestBody RenameParentRequest renameParentRequest) {
        departmentapiService.renameParentDepartment(renameParentRequest);
        return Response.newSuccess(null, "Successfully renamed parent department.");
    }
    @DeleteMapping("/delete-sub")
    public Response<?> deleteSubDepartment(@RequestBody DeletSubRequest deletSubRequest) {
        departmentapiService.deleteSubDepartment(deletSubRequest);
        return Response.newSuccess(null, "Successfully deleted sub-department.");
    }

    @DeleteMapping("/delete-parent")
    public Response<?> deleteParentDepartment(@RequestBody DeletParentRequest deletParentRequest) {
        departmentapiService.deleteParentDepartment(deletParentRequest);
        return Response.newSuccess(null, "Successfully deleted parent department.");
    }

    @GetMapping("/parent-list")
    public Response<?> getAllDepartments() {
        return Response.newSuccess(departmentapiService.getAllDepartments(), "Successfully retrieved all departments.");
    }

    @GetMapping("/sub-list")
    public Response<?> getSubDepartmentsByParentName(@RequestBody SubListRequest subListRequest) {
        return Response.newSuccess(departmentapiService.getSubDepartmentsByParentName(subListRequest), "Successfully retrieved sub-departments.");
    }

}



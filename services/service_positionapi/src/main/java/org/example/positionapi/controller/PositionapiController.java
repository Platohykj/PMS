package org.example.positionapi.controller;


import org.example.position.model.Position;
import org.example.positionapi.request.RemoveEmployeePositionRequest;
import org.example.positionapi.request.SaveRequest;
import org.example.positionapi.request.UpdateEmployeeRequest;
import org.example.positionapi.service.PositionapiService;
import org.example.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/position")
public class PositionapiController {
    @Autowired
    private PositionapiService positionapiService;

    @GetMapping("/list")
    public Response<?> getPositionList() {
        return Response.newSuccess(positionapiService.getPositionList(), "Successfully retrieved position list.");
    }

    @PostMapping("/save")
    public Response<?> savePosition(@RequestBody Position position) {
        try {
            positionapiService.savePosition(position);
            return Response.newSuccess(null, "Position saved successfully.");
        } catch (Exception e) {
            return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error saving position: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public Response<?> deletePosition(@PathVariable("id") Long id) {
        try {
            positionapiService.deletePosition(id);
            return Response.newSuccess(null, "Position deleted successfully.");
        } catch (Exception e) {
            return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error deleting position: " + e.getMessage());
        }
    }

    @GetMapping("/employees")
    public Response<?> getEmployeesByPosition(@RequestParam("department") String department) {
        try {
            return Response.newSuccess(positionapiService.getEmployeesByPosition(department), "Successfully retrieved employees for position.");
        } catch (Exception e) {
            return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error retrieving employees: " + e.getMessage());
        }
    }

    @PostMapping("/update-employee")
    public Response<?> updateEmployeePosition(@RequestBody UpdateEmployeeRequest updateEmployeeRequest) {
        try {
            positionapiService.updateEmployeePosition(updateEmployeeRequest);
            return Response.newSuccess(null, "Employee position updated successfully.");
        } catch (Exception e) {
            return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error updating employee position: " + e.getMessage());
        }
    }

    @PostMapping("/remove-employee-position")
    public Response<?> removeEmployeePosition(@RequestBody RemoveEmployeePositionRequest removeEmployeePositionRequest) {
        try {
            positionapiService.removeEmployeePosition(removeEmployeePositionRequest);
            return Response.newSuccess(null, "Employee position removed successfully.");
        } catch (Exception e) {
            return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error removing employee position: " + e.getMessage());
        }
    }
}



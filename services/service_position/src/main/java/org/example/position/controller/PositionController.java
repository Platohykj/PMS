package org.example.position.controller;

import org.example.position.model.Position;
import org.example.position.service.PositionService;
import org.example.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/db/position")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @PostMapping("/addposition")
    public Response<?> addPosition(@RequestBody Position position) {
        positionService.addPosition(position);
        return Response.newSuccess(position, "Position added successfully");
    }
    @GetMapping("/getpositionbyid")
    public Response<?> getPositionById(@RequestParam Long id) {
        List<Position> position = positionService.getPositionById(id);
        if (position != null) {
            return Response.newSuccess(position, "Position retrieved successfully");
        } else {
            return Response.newError(HttpStatus.NOT_FOUND.value(), "Position not found");
        }
    }
    @GetMapping("/getallpositions")
    public Response<?> getAllPositions() {
        List<Position> positions = positionService.getAllPositions();
        if (positions != null && !positions.isEmpty()) {
            return Response.newSuccess(positions, "Positions retrieved successfully");
        } else {
            return Response.newError(HttpStatus.NOT_FOUND.value(), "No positions found");
        }
    }
    @GetMapping("/getpositionbyname")
    public Response<?> getPositionByName(@RequestParam String name) {
        List<Position> positions = positionService.getAllPositions();
        List<Position> filteredPositions = positions.stream()
                .filter(position -> position.getName().equalsIgnoreCase(name))
                .toList();
        if (!filteredPositions.isEmpty()) {
            return Response.newSuccess(filteredPositions, "Positions retrieved successfully");
        } else {
            return Response.newError(HttpStatus.NOT_FOUND.value(), "No positions found with the given name");
        }
    }
    @DeleteMapping("/deleteposition")
    public Response<?> deletePosition(@RequestParam Long id) {
        List<Position> positions = positionService.getPositionById(id);
        if (positions != null && !positions.isEmpty()) {
            positionService.deletePosition(id);
            return Response.newSuccess(null, "Position deleted successfully");
        } else {
            return Response.newError(HttpStatus.NOT_FOUND.value(), "Position not found");
        }
    }

}

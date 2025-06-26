package org.example.modification.controller;

import org.example.modification.model.ModificationApplication;
import org.example.modification.service.ModificationService;
import org.example.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/db/modification")
public class ModificationController {
    @Autowired
    private ModificationService modificationService;

    @GetMapping("/getallmodifications")
    public Response<?> getAllModifications() {
        return Response.newSuccess(modificationService.getAllModifications(), "All modifications retrieved successfully");
    }
    @PostMapping("/addmodification")
    public Response<?> addModification(@RequestBody ModificationApplication modificationApplication) {
        return Response.newSuccess(modificationService.addModification(modificationApplication), "Modification added successfully");
    }
    @PutMapping("/updatestatus")
    public Response<?> updateStatus(@RequestParam("id") Long id, @RequestParam("status") String status) {
        ModificationApplication modification = modificationService.updateStatus(id, status);
        if (modification != null) {
            return Response.newSuccess(modification, "Modification status updated successfully");
        } else {
            return Response.newError(HttpStatus.NOT_FOUND.value(), "Modification status not found");
        }
    }
}

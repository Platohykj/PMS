package org.example.hrapi.controller;



import org.example.hrapi.request.BatchSalary;
import org.example.hrapi.request.ListRequest;
import org.example.hrapi.request.UpadteRequest;
import org.example.hrapi.service.HrapiService;
import org.example.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hr")
public class HrapiController {
    @Autowired
    HrapiService hrapiService;

    @PostMapping("/list")
    public Response<?> list(@RequestBody ListRequest request) {
        return Response.newSuccess(hrapiService.list(request), "List of HR API resources");
    }

    @PostMapping("/update")
    public Response<?> update(@RequestBody UpadteRequest request) {
        hrapiService.update(request);
        return Response.newSuccess(null, "Update successful");
    }

    @PostMapping("/batchSalary")
    public Response<?> batchSalary(@RequestBody BatchSalary request) {
        hrapiService.batchSalary(request);
        return Response.newSuccess(null, "Batch salary update successful");
    }

}



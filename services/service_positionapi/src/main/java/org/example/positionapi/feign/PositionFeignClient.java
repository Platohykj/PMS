package org.example.positionapi.feign;

import org.example.position.model.Position;
import org.example.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-position")
public interface PositionFeignClient {
    @GetMapping("/api/db/position/getallpositions")
    public Response<?> getAllPositions();

    @PostMapping("/api/db/position/addposition")
    public Response<?> addPosition(@RequestBody Position position);

    @DeleteMapping("/api/db/position/deleteposition")
    public Response<?> deletePosition(@RequestParam Long id);
    @GetMapping("/api/db/position/getpositionbyid")
    public Response<?> getPositionById(@RequestParam Long id);

    @GetMapping("/api/db/position/getpositionbyname")
    public Response<?> getPositionByName(@RequestParam String name);

}

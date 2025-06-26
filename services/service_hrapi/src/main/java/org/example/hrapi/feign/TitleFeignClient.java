package org.example.hrapi.feign;


import org.example.response.Response;
import org.example.title.model.Title;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-title")

public interface TitleFeignClient {
    @GetMapping("/api/db/title/getalltitle")
    public Response<?> getAllTitle();

    @PutMapping("/api/db/title/updatetitle")
    public Response<?> updateTitle(@RequestBody Title title);

    @PostMapping("/api/db/title//addtitle")
    public Response<?> addTitle(@RequestBody Title title);

    @DeleteMapping("/api/db/title/deletetitle")
    public Response<?> deleteTitle(@RequestParam("id") Long id);

    @GetMapping("/api/db/title/gettitlebyid")
    public Title getTitleById(@RequestParam("id") Long id);
}

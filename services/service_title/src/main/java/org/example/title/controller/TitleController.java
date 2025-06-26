package org.example.title.controller;



import org.example.response.Response;
import org.example.title.model.Title;
import org.example.title.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/db/title")
public class TitleController {
    @Autowired
    private TitleService titleService;

    @GetMapping("/getalltitle")
    public Response<?> getAllTitle() {
        return Response.newSuccess(titleService.getAllTitle(), "Successfully retrieved all titles.");
    }

    @PostMapping("/addtitle")
    public Response<?> addTitle(@RequestBody Title title) {
        return Response.newSuccess(titleService.addTitle(title), "Successfully added new title.");
    }

    @PutMapping("/updatetitle")
    public Response<?> updateTitle(@RequestBody Title title) {
        return Response.newSuccess(titleService.updateTitle(title), "Successfully updated title.");
    }

    @DeleteMapping("/deletetitle")
    public Response<?> deleteTitle(@RequestParam("id") Long id) {
        titleService.deleteTitle(id);
        return Response.newSuccess(null, "Successfully deleted title with ID: " + id);
    }

    @GetMapping("/gettitlebyid")
    public Title getTitleById(@RequestParam("id") Long id) {
        Title title = titleService.getTitleById(id);
        if (title == null) {
            throw new RuntimeException("Title not found with ID: " + id);
        }
        return title;
    }
}

package com.smartreal.api.controller;

import com.smartreal.api.model.Project;
import com.smartreal.api.model.User;
import com.smartreal.api.service.ProjectService;
import com.smartreal.api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping
    public List<Project> getListProject(@RequestParam(defaultValue = "") String subject,
                                        @RequestParam(defaultValue = "0") long minPrice,
                                        @RequestParam(defaultValue = "999999999999") long maxPrice,
                                        @RequestParam(defaultValue = "0") int offset,
                                        @RequestParam(defaultValue = "20") int size) {
        User currentUser = userService.getCurrentUser();
        return projectService.getListProject(currentUser.getId(), subject, minPrice, maxPrice, offset, size);
    }

    @GetMapping("/{id}")
    public Project getProjectDetail(@PathVariable long id) {
        User currentUser = userService.getCurrentUser();
        return projectService.getProjectDetail(currentUser.getId(), id);
    }
}

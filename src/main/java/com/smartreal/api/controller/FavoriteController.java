package com.smartreal.api.controller;

import com.smartreal.api.model.Project;
import com.smartreal.api.model.User;
import com.smartreal.api.model.response.Like;
import com.smartreal.api.service.ProjectService;
import com.smartreal.api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {
    private final UserService userService;
    private final ProjectService projectService;

    public FavoriteController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @PostMapping("/project/{id}")
    public Like likeProject(@PathVariable long id) {
        User currentUser = userService.getCurrentUser();
        return projectService.toggleFavorite(currentUser.getId(), id);
    }

    @GetMapping("/project")
    public List<Project> getFavoriteProject(@RequestParam(defaultValue = "0") int offset,
                                            @RequestParam(defaultValue = "20") int size) {
        User currentUser = userService.getCurrentUser();
        return projectService.getListFavoriteProjectByUserId(currentUser.getId(), offset, size);
    }
}

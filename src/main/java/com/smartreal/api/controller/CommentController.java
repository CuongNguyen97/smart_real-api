package com.smartreal.api.controller;

import com.smartreal.api.model.Comment;
import com.smartreal.api.model.CommentListItem;
import com.smartreal.api.model.response.Count;
import com.smartreal.api.service.CommentService;
import com.smartreal.api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping
    public Comment writeComment(@RequestBody Comment comment) {
        comment.setUserId(userService.getCurrentUser().getId());

        return commentService.insertComment(comment);
    }

    @GetMapping("/project/{projectId}/count")
    public Count getCommentCountByProject(@PathVariable long projectId) {
        return commentService.getCommentCount(projectId);
    }

    @GetMapping("/project/{projectId}")
    public List<CommentListItem> getCommentListByProject(@PathVariable long projectId,
                                                         @RequestParam(defaultValue = "0") long offset,
                                                         @RequestParam(defaultValue = "20") int size) {
        return commentService.getCommentList(projectId, offset, size);
    }
}

package com.smartreal.api.service;

import com.smartreal.api.mapper.CommentMapper;
import com.smartreal.api.model.Comment;
import com.smartreal.api.model.CommentListItem;
import com.smartreal.api.model.response.Count;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    private final CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public Count getCommentCount(long projectId) {
        return Count.of(commentMapper.getCommentCount(projectId));
    }

    public List<CommentListItem> getCommentList(long projectId, long offset, int size) {
        return commentMapper.getCommentList(projectId, offset, size);
    }

    @Transactional
    public Comment insertComment(Comment comment) {
        commentMapper.insertComment(comment);

        return commentMapper.getCommentById(comment.getId());
    }
}

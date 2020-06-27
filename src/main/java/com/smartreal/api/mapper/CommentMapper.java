package com.smartreal.api.mapper;

import com.smartreal.api.model.Comment;
import com.smartreal.api.model.CommentListItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    long getCommentCount(@Param("projectId") long projectId);

    List<CommentListItem> getCommentList(@Param("projectId") long projectId, @Param("offset") long offset, @Param("size") int size);

    void insertComment(Comment comment);

    Comment getCommentById(@Param("id") long id);
}

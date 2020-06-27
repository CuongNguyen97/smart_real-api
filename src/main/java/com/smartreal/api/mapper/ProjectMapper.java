package com.smartreal.api.mapper;

import com.smartreal.api.model.Project;
import com.smartreal.api.model.UserFavorite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {

    List<Project> getListProject(@Param("userId") Long userId,
                                 @Param("subject") String subject,
                                 @Param("minPrice") long minPrice,
                                 @Param("maxPrice") long maxPrice,
                                 @Param("offset") int offset,
                                 @Param("size") int size);

    Project getProjectDetail(@Param("userId") Long userId, @Param("projectId") long projectId);

    List<Project> getListFavoriteProject(@Param("userId") Long userId, @Param("offset") int offset, @Param("size") int size);

    void insertUserFavorite(@Param("userId") Long userId, @Param("projectId") long projectId);

    UserFavorite getUserFavoriteByUserAndProjectId(@Param("userId") long userId, @Param("projectId") long projectId);

    void deleteUserFavorite(@Param("userId") Long userId, @Param("projectId") long projectId);
}

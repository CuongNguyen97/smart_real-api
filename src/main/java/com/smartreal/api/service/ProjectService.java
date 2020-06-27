package com.smartreal.api.service;

import com.smartreal.api.mapper.ProjectMapper;
import com.smartreal.api.model.Project;
import com.smartreal.api.model.UserFavorite;
import com.smartreal.api.model.response.Like;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ProjectService {
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    public List<Project> getListProject(Long userId, String subject, long minPrice, long maxPrice, int offset, int size) {
        return projectMapper.getListProject(userId, subject, minPrice, maxPrice, offset, size);
    }

    public Project getProjectDetail(Long userId, long projectId) {
        return projectMapper.getProjectDetail(userId, projectId);
    }

    public List<Project> getListFavoriteProjectByUserId(Long userId, int offset, int size) {
        return projectMapper.getListFavoriteProject(userId, offset, size);
    }

    @Transactional
    public Like toggleFavorite(long userId, long projectId) {
        UserFavorite userFavorite = projectMapper.getUserFavoriteByUserAndProjectId(userId, projectId);
        if (Objects.isNull(userFavorite)) {
            projectMapper.insertUserFavorite(userId, projectId);

            return Like.like();
        } else {
            projectMapper.deleteUserFavorite(userId, projectId);

            return Like.unLike();
        }
    }
}

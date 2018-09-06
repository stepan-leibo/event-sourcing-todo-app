package com.sleibo.es.project.persistence;

import com.sleibo.es.project.domain.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, String> {
    boolean existsByName(String name);
}

package com.sleibo.es.project.domain;

import com.sleibo.es.domain.Project;

import javax.persistence.Entity;

@Entity
public class ProjectEntity extends Project {
    public ProjectEntity() {
    }

    public ProjectEntity(String id, String name) {
        super(id, name);
    }
}

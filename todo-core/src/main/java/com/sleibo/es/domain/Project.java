package com.sleibo.es.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Inheritance
public class Project {
    @Id
    private String id;

    private String name;

    @ElementCollection
    private Set<UUID> todoIds = new HashSet<>();

    public Project() {
    }

    public Project(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UUID> getTodoIds() {
        return todoIds;
    }

    public void addTodoIds(Collection<UUID> ids) {
        todoIds.addAll(ids);
    }

    public void removeTodoIds(Collection<UUID> ids) {
        todoIds.removeAll(ids);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }

        Project project = (Project) o;

        if (getId() != null ? !getId().equals(project.getId()) : project.getId() != null) {
            return false;
        }
        if (getName() != null ? !getName().equals(project.getName()) : project.getName() != null) {
            return false;
        }
        return getTodoIds() != null ? getTodoIds().equals(project.getTodoIds()) : project.getTodoIds() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getTodoIds() != null ? getTodoIds().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Project{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", todoIds=").append(todoIds);
        sb.append('}');
        return sb.toString();
    }
}

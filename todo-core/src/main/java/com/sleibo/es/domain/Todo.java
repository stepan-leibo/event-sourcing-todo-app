package com.sleibo.es.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import java.util.Objects;

@Entity
@Inheritance
public class Todo {
    // id works as @AggregateIdentifier
    @Id
    private String id;

    private String text;

    private boolean completed;

    public Todo() {
    }

    public Todo(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Todo)) {
            return false;
        }
        Todo todo = (Todo) o;
        return isCompleted() == todo.isCompleted() &&
            Objects.equals(getId(), todo.getId()) &&
            Objects.equals(getText(), todo.getText());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getText(), isCompleted());
    }
}

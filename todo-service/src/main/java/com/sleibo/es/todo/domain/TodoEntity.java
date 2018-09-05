package com.sleibo.es.todo.domain;

import com.sleibo.es.domain.Todo;

import javax.persistence.Entity;

@Entity
public class TodoEntity extends Todo {
    public TodoEntity() {
    }

    public TodoEntity(String id, String text) {
        super(id, text);
    }
}

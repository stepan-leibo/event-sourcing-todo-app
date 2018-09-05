package com.sleibo.es.todo.persistence;

import com.sleibo.es.todo.domain.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, String> {
}

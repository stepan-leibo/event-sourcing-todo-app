package com.sleibo.es.events

import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.util.*

data class CreateTodoCommand(@TargetAggregateIdentifier val id: String, val text: String)
data class CompleteTodoCommand(@TargetAggregateIdentifier val id: String)
data class UncompleteTodoCommand(@TargetAggregateIdentifier val id: String)

data class CreateProjectCommand(@TargetAggregateIdentifier val id: String, val text: String)
data class AddTodosToProjectCommand(@TargetAggregateIdentifier val projectId: String, val todoIds: Collection<UUID>)
data class RemoveTodosFromProjectCommand(@TargetAggregateIdentifier val projectId: String, val todoId: Collection<UUID>)
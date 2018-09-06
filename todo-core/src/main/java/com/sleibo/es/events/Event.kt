package com.sleibo.es.events

import java.util.*

data class TodoCreatedEvent(val id: String, val text: String)
data class TodoCompletedEvent(val id: String)
data class TodoUnompletedEvent(val id: String)

data class ProjectCreatedEvent(val id: String, val name: String)

data class TodosAddedToProjectEvent(val projectId: String, val todoIds: Collection<UUID>)
data class TodosRemovedFromProjectEvent(val projectId: String, val todoIds: Collection<UUID>)
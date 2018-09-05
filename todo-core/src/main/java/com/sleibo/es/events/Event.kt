package com.sleibo.es.events

data class TodoCreatedEvent(val id: String, val text: String)
data class TodoCompletedEvent(val id: String)
data class TodoUnompletedEvent(val id: String)
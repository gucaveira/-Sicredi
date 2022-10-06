package com.example.core.domain.model

data class Events(val eventsItem: List<EventsItem>)

data class EventsItem(
    val date: Long,
    val description: String,
    val id: String,
    val image: String,
    val latitude: Double,
    val longitude: Double,
    val people: List<Any>,
    val price: Double,
    val title: String
)
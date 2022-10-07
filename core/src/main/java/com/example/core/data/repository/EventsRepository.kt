package com.example.core.data.repository

import com.example.core.domain.model.Events

interface EventsRepository {
    suspend fun fetchEvents(): Events
}
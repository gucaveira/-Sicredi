package com.example.core.data.repository

import com.example.core.domain.model.CheckIn
import com.example.core.domain.model.Events

interface EventsRemoteDataSource {
    suspend fun fetchEvents(): Events
    suspend fun checkIn(checkIn: CheckIn)
}

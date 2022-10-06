package com.example.core.data.repository

import com.example.core.domain.model.Events

interface SicrediRepository {
    suspend fun fetchEvents(): Events
}
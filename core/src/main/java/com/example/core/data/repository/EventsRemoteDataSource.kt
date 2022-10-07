package com.example.core.data.repository

import com.example.core.domain.model.Events

interface EventsRemoteDataSource {
   suspend fun fetchEvents():  Events
}

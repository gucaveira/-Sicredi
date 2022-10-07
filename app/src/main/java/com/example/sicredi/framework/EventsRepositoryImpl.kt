package com.example.sicredi.framework

import com.example.core.data.repository.EventsRemoteDataSource
import com.example.core.data.repository.EventsRepository
import com.example.core.domain.model.Events

class EventsRepositoryImpl(private val remoteDataSource: EventsRemoteDataSource) : EventsRepository {
    override suspend fun fetchEvents(): Events {
        return remoteDataSource.fetchEvents()
    }
}
package com.example.sicredi.remote

import com.example.core.data.repository.EventsRemoteDataSource
import com.example.core.domain.model.CheckIn
import com.example.core.domain.model.Events
import com.example.sicredi.framework.network.Api

class RetrofitDataSource(private val api: Api) : EventsRemoteDataSource {
    override suspend fun fetchEvents(): Events {
        return api.fetchEvents()
    }

    override suspend fun checkIn(checkIn: CheckIn) {
        return api.checkIn(checkIn)
    }
}
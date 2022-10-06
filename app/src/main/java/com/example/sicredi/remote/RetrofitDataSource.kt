package com.example.sicredi.remote

import com.example.core.data.repository.SicrediRemoteDataSource
import com.example.core.domain.model.Events
import com.example.sicredi.framework.network.Api

class RetrofitDataSource(private val api: Api) : SicrediRemoteDataSource {
    override suspend fun fetchEvents(): Events {
        return api.fetchEvents()
    }
}
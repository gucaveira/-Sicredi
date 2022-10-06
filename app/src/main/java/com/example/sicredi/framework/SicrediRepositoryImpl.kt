package com.example.sicredi.framework

import com.example.core.data.repository.SicrediRemoteDataSource
import com.example.core.data.repository.SicrediRepository
import com.example.core.domain.model.Events

class SicrediRepositoryImpl(private val remoteDataSource: SicrediRemoteDataSource) : SicrediRepository {
    override suspend fun fetchEvents(): Events {
        return remoteDataSource.fetchEvents()
    }
}
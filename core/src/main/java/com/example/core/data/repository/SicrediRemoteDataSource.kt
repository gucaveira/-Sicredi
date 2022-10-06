package com.example.core.data.repository

import com.example.core.domain.model.Events

interface SicrediRemoteDataSource {
   suspend fun fetchEvents():  Events
}

package com.example.sicredi.framework.network

import com.example.core.domain.model.Events
import retrofit2.http.GET

interface Api {
    @GET("events")
   suspend fun fetchEvents():Events
}
package com.example.sicredi.framework.network

import com.example.core.domain.model.CheckIn
import com.example.core.domain.model.Events
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @GET("events")
    suspend fun fetchEvents(): Events

    @POST("checkin")
    suspend fun checkIn(@Body checkIn: CheckIn)
}
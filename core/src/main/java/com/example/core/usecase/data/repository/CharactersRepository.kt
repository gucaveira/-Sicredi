package com.example.core.usecase.data.repository

import com.example.core.domain.model.Events

interface CharactersRepository {
    fun fetchEvents(): Events

}
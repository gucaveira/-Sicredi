package com.example.core.usecase

import com.example.core.data.repository.EventsRepository
import com.example.core.domain.model.Events
import com.example.core.usecase.base.CoroutinesDispatchers
import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UserCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


interface GetEventsUseCase {
    operator fun invoke(params: Unit): Flow<ResultStatus<Events>>
}

class GetEventUseCaseImpl(
    private val repository: EventsRepository,
    private val dispatchers: CoroutinesDispatchers
) : UserCase<Unit, Events>(), GetEventsUseCase {

    override suspend fun doWork(params: Unit): ResultStatus<Events> {
        return withContext(dispatchers.io()) {
            ResultStatus.Success(repository.fetchEvents())
        }
    }
}

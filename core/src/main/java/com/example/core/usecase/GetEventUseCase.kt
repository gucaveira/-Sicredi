package com.example.core.usecase

import com.example.core.data.repository.SicrediRepository
import com.example.core.domain.model.Events
import com.example.core.usecase.base.CoroutinesDispatchers
import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UserCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


interface GetEventsUseCase {
    operator fun invoke(): Flow<ResultStatus<Events>>
}

class GetEventUseCaseImpl(
    private val repository: SicrediRepository,
    private val dispatchers: CoroutinesDispatchers
) : UserCase<Events>(), GetEventsUseCase {

    override suspend fun doWork(): ResultStatus<Events> {
        return withContext(dispatchers.io()) {
            ResultStatus.Success(repository.fetchEvents())
        }
    }
}

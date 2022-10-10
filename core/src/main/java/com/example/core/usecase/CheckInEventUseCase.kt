package com.example.core.usecase

import com.example.core.data.repository.EventsRepository
import com.example.core.domain.model.CheckIn
import com.example.core.usecase.base.CoroutinesDispatchers
import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UserCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


interface CheckInEventUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<Unit>>

    data class Params(
        val eventId: String,
        val name: String,
        val email: String
    )
}

class CheckInEventUseCaseImpl(
    private val repository: EventsRepository,
    private val dispatchers: CoroutinesDispatchers
) : UserCase<CheckInEventUseCase.Params, Unit>(), CheckInEventUseCase {

    override suspend fun doWork(params: CheckInEventUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            ResultStatus.Success(
                repository.checkIn(
                    CheckIn(
                        params.eventId,
                        params.name,
                        params.email
                    )
                )
            )
        }
    }
}

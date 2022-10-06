package com.example.core.usecase.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

abstract class UserCase<out R> {

    operator fun invoke(): Flow<ResultStatus<R>> = flow {
        emit(ResultStatus.Loading)
        emit(doWork())
    }.catch { throwable ->
        emit(ResultStatus.Error(throwable))
    }

    protected abstract suspend fun doWork(): ResultStatus<R>
}
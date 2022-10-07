package com.example.sicredi.presentation.eventslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Events
import com.example.core.usecase.GetEventsUseCase
import com.example.core.usecase.base.ResultStatus
import kotlinx.coroutines.launch

class EventsViewModel(
    private val useCase: GetEventsUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    fun fetchEvent() {
        viewModelScope.launch {
            useCase().collect { status ->
                _uiState.value = when (status) {
                    ResultStatus.Loading -> UiState.Loading

                    is ResultStatus.Success -> {
                        if (status.data.isEmpty()) {
                            UiState.Empty
                        } else UiState.Success(status.data)
                    }

                    is ResultStatus.Error -> UiState.Error
                }
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val detailParentList: Events) : UiState()
        object Error : UiState()
        object Empty : UiState()
    }
}
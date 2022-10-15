package com.example.sicredi.presentation.eventslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Events
import com.example.core.usecase.CheckInEventUseCase
import com.example.core.usecase.GetEventsUseCase
import com.example.core.usecase.base.ResultStatus
import kotlinx.coroutines.launch

class EventsViewModel(
    private val getEventsUseCase: GetEventsUseCase,
    private val checkInEventUseCase: CheckInEventUseCase
) : ViewModel() {

    private val _uiStateGetEvents = MutableLiveData<UiState>()
    val uiStateGetEvents: LiveData<UiState> get() = _uiStateGetEvents

    private val _uiStateCheckIn = MutableLiveData<UiStateCheckIn>()
    val uiStateCheckIn: LiveData<UiStateCheckIn> get() = _uiStateCheckIn

    fun fetchEvent() {
        viewModelScope.launch {
            getEventsUseCase().collect { status ->
                _uiStateGetEvents.value = when (status) {
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

    fun checkInEvent(eventId: String, name: String, email: String) {
        viewModelScope.launch {
            checkInEventUseCase(CheckInEventUseCase.Params(eventId, name, email)).collect { status ->
                _uiStateCheckIn.value = when (status) {
                    ResultStatus.Loading -> UiStateCheckIn.Loading
                    is ResultStatus.Success -> UiStateCheckIn.Success
                    is ResultStatus.Error -> UiStateCheckIn.Error
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

    sealed class UiStateCheckIn {
        object Loading : UiStateCheckIn()
        object Success : UiStateCheckIn()
        object Error : UiStateCheckIn()
    }
}
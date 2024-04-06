package com.ma.mytodo.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ma.mytodo.data.TodoRepository
import com.ma.mytodo.domain.TodoDetails
import com.ma.mytodo.domain.TodoPriority
import com.ma.mytodo.domain.TodoRepeatDate
import com.ma.mytodo.domain.TodoTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddTodoUiModel())
    val uiState = _uiState.asStateFlow()

    private fun updateState(updateAction: AddTodoUiModel.() -> AddTodoUiModel) {
        _uiState.update { updateAction(it) }
    }

    fun titleChanged(title: String) {
        updateState {
            copy(title = this.title.copy(value = title))
        }
    }

    fun addHourClicked() {
        if (_uiState.value.time.hour == 23) return
        updateState {
            copy(time = time.copy(hour = time.hour + 1))
        }
    }

    fun addMinuteClicked() {
        if (_uiState.value.time.minute == 59) return
        updateState {
            copy(time = time.copy(minute = time.minute + 1))
        }
    }

    fun minusHourClicked() {
        if (_uiState.value.time.hour == 0) return
        updateState {
            copy(time = time.copy(hour = time.hour - 1))
        }
    }

    fun minusMinuteClicked() {
        if (_uiState.value.time.minute == 0) return
        updateState {
            copy(time = time.copy(minute = time.minute - 1))
        }
    }

    fun saveButtonClicked() {
        val title = _uiState.value.title.value
        if (title == null || title.length !in 3..40) {
            updateState {
                copy(title = this.title.copy(hasError = true))
            }
            return
        }
        val time = _uiState.value.time
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val currentMinute = Calendar.getInstance().get(Calendar.MINUTE)
        if (time.hour < currentHour || (time.hour == currentHour && time.minute < currentMinute)) {
            updateState {
                copy(time = this.time.copy(hasError = true))
            }
            return
        }

        viewModelScope.launch {
            todoRepository.saveTodo(createTodoDetails(title, time))
        }
    }

    private fun createTodoDetails(
        title: String,
        time: TodoTimeUiModel
    ) = TodoDetails(
        title = title,
        description = null,
        repeatDate = TodoRepeatDate.Daily(
            TodoTime(time.hour, time.minute)
        ),
        priority = TodoPriority.High,
    )

}
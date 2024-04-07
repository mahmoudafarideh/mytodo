package com.ma.mytodo.ui.add

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(AddTodoUiModel())
    val uiState = _uiState.asStateFlow()

    private fun updateState(action: (AddTodoUiModel) -> AddTodoUiModel) {
        _uiState.update(action)
    }

    fun titleChanged(title: String) {
        updateState { it.copy(title = it.title.copy(value = title)) }
    }

    fun descriptionChanged(description: String) {
        updateState { it.copy(description = it.description.copy(value = description)) }
    }

    fun timeChanged(hour: Int, minute: Int) {
        updateState {
            it.copy(
                dateTime = it.dateTime.copy(
                    time = it.dateTime.time.copy(
                        hour = hour.coerceIn(0, 23),
                        minute = minute.coerceIn(0, 59)
                    )
                )
            )
        }
    }

    fun switchToSingleRepeatDateClicked() {
        updateState {
            it.copy(
                dateTime = it.dateTime.copy(repeatDate = getTodayDate(), time = getCurrentTime())
            )
        }
    }

    private fun getTodayDate(): TodoRepeatDateUiModel.Single {
        val calendar = Calendar.getInstance()
        return TodoRepeatDateUiModel.Single(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH),
        )
    }

    private fun getCurrentTime(): TodoTimeUiModel {
        val calendar = Calendar.getInstance()
        return TodoTimeUiModel(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE))
    }

}
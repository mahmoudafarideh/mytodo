package com.ma.mytodo.ui.add

import androidx.lifecycle.ViewModel
import com.ma.mytodo.utils.CalendarProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val calendarProvider: CalendarProvider,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddTodoUiModel())
    val uiState = _uiState.asStateFlow()

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

    fun prioritySelected(priority: TodoPriorityUiModel) {
        updateState {
            it.copy(priorityUiModel = priority)
        }
    }

    private fun getTodayDate(): TodoRepeatDateUiModel.Single {
        val calendar = calendarProvider.getCalendar()
        return TodoRepeatDateUiModel.Single(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH),
        )
    }

    private fun getCurrentTime(): TodoTimeUiModel {
        val calendar = calendarProvider.getCalendar()
        return TodoTimeUiModel(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))
    }

    private fun updateState(action: (AddTodoUiModel) -> AddTodoUiModel) {
        _uiState.update(action)
    }

}
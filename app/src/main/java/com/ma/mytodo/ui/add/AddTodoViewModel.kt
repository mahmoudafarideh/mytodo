package com.ma.mytodo.ui.add

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
                    time = TodoTimeUiModel(
                        hour.coerceIn(0, 23),
                        minute.coerceIn(0, 59)
                    )
                )
            )
        }
    }

}
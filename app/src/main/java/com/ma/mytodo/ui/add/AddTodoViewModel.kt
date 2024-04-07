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

    fun titleChanged(title: String) {
        _uiState.update { it.copy(title = it.title.copy(value = title)) }
    }

    fun descriptionChanged(description: String) {
        _uiState.update { it.copy(description = it.description.copy(value = description)) }
    }

}
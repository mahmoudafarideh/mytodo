package com.ma.mytodo.ui.add

data class AddTodoUiModel(
    val title: TodoTitleUiModel = TodoTitleUiModel(),
    val time: TodoTimeUiModel = TodoTimeUiModel(),
)

data class TodoTitleUiModel(
    val value: String? = null,
    val hasError: Boolean = false
)

data class TodoTimeUiModel(
    val hour: Int = 0,
    val minute: Int = 0,
    val hasError: Boolean = false
)





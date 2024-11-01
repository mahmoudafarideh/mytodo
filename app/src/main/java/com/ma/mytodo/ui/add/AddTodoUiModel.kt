package com.ma.mytodo.ui.add

data class AddTodoUiModel(
    val title: TodoTitleUiModel = TodoTitleUiModel(),
    val description: TodoDescriptionUiModel = TodoDescriptionUiModel(),
    val dateTime: TodoDateTimeUiModel = TodoDateTimeUiModel(),
    val priorityUiModel: TodoPriorityUiModel = TodoPriorityUiModel.Medium
)

enum class TodoPriorityUiModel {
    High,
    Medium,
    Low
}

data class TodoTitleUiModel(
    val value: String? = null,
    val hasError: Boolean = false
)

data class TodoDescriptionUiModel(
    val value: String? = null,
    val hasError: Boolean = false
)

data class TodoDateTimeUiModel(
    val repeatDate: TodoRepeatDateUiModel = TodoRepeatDateUiModel.Daily,
    val time: TodoTimeUiModel = TodoTimeUiModel(),
    val hasError: Boolean = false
)

sealed class TodoRepeatDateUiModel {
    data class Single(
        val year: Int,
        val month: Int,
        val day: Int
    ) : TodoRepeatDateUiModel()

    data object Daily : TodoRepeatDateUiModel()
}

data class TodoTimeUiModel(
    val hour: Int = 0,
    val minute: Int = 0
)





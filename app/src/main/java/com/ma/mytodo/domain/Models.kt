package com.ma.mytodo.domain

data class Todo(
    val id: Long,
    val details: TodoDetails,
    val isCompleted: Boolean,
)

data class TodoDetails(
    val title: String,
    val description: String? = null,
    val repeatDate: TodoRepeatDate,
    val priority: TodoPriority,
)

enum class TodoPriority {
    Low,
    Medium,
    High
}

sealed class TodoRepeatDate {
    abstract val time: TodoTime

    data class Single(val date: TodoDate, override val time: TodoTime) : TodoRepeatDate()
    data class Daily(override val time: TodoTime) : TodoRepeatDate()
}

data class TodoDate(
    val year: Int,
    val month: Int,
    val day: Int
)

data class TodoTime(
    val hour: Int,
    val minute: Int
)
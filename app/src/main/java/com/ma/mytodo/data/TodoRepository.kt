package com.ma.mytodo.data

import com.ma.mytodo.data.source.TodoDao
import com.ma.mytodo.data.source.TodoDateEntity
import com.ma.mytodo.data.source.TodoEntity
import com.ma.mytodo.data.source.TodoPriorityEntity
import com.ma.mytodo.data.source.TodoTimeEntity
import com.ma.mytodo.domain.TodoDetails
import com.ma.mytodo.domain.TodoPriority
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
) {

    suspend fun saveTodo(todoDetails: TodoDetails) {
        withContext(Dispatchers.IO) {
            todoDao.insert(todoDetails.toTodoEntity())
        }
    }

    private fun TodoPriority.toTodoPriorityEntity() = when (this) {
        TodoPriority.Low -> TodoPriorityEntity.Low
        TodoPriority.Medium -> TodoPriorityEntity.Medium
        TodoPriority.High -> TodoPriorityEntity.High
    }

    private fun getTodayDate(): TodoDateEntity {
        val calendar = Calendar.getInstance()
        return TodoDateEntity(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
        )
    }

    private fun TodoDetails.toTodoEntity(): TodoEntity {
        return TodoEntity(
            id = 0,
            title = title,
            description = description,
            priority = priority.toTodoPriorityEntity(),
            isCompleted = false,
            time = TodoTimeEntity(
                this.repeatDate.time.hour, this.repeatDate.time.minute
            ),
            date = getTodayDate()
        )
    }
}
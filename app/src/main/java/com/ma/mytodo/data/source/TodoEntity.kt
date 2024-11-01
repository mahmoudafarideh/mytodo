package com.ma.mytodo.data.source

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String?,
    val priority: TodoPriorityEntity,
    val isCompleted: Boolean,
    @Embedded
    val time: TodoTimeEntity,
    val date: TodoDateEntity
)

data class TodoDateEntity(
    val year: Int,
    val month: Int,
    val day: Int
)

data class TodoTimeEntity(
    val hour: Int,
    val minute: Int,
)

enum class TodoPriorityEntity {
    Low,
    Medium,
    High
}
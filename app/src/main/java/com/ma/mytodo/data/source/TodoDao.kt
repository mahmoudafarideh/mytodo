package com.ma.mytodo.data.source

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface TodoDao {
    @Insert
    suspend fun insert(todo: TodoEntity)
}
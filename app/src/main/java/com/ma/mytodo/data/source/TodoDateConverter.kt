package com.ma.mytodo.data.source

import androidx.room.TypeConverter

class TodoDateConverter {

    @TypeConverter
    fun stringToDate(value: String): TodoDateEntity {
        val date = value.split('-').map { it.toInt() }
        return TodoDateEntity(date[0], date[1], date[2])
    }

    @TypeConverter
    fun dateToString(value: TodoDateEntity): String {
        return "${value.year}-${value.month}-${value.day}"
    }
}
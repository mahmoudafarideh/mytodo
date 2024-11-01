package com.ma.mytodo.di

import android.content.Context
import androidx.room.Room
import com.ma.mytodo.data.source.TodoDatabase
import com.ma.mytodo.utils.CalendarProvider
import com.ma.mytodo.utils.CalendarProviderImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): TodoDatabase = Room.databaseBuilder(
        context, TodoDatabase::class.java, "todo.db"
    ).build()

    @Provides
    fun todoDao(todoDatabase: TodoDatabase) = todoDatabase.todoDao()
}

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModules {
    @Binds
    fun bindCalendarProvider(calendarProviderImp: CalendarProviderImp): CalendarProvider
}
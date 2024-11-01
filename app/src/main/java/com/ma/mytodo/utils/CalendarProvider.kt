package com.ma.mytodo.utils

import java.util.Calendar
import javax.inject.Inject

interface CalendarProvider {
    fun getCalendar(): Calendar
}

class CalendarProviderImp @Inject constructor() : CalendarProvider {
    override fun getCalendar(): Calendar = Calendar.getInstance()
}
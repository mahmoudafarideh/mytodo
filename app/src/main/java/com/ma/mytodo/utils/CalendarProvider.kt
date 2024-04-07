package com.ma.mytodo.utils

import java.util.Calendar

interface CalendarProvider {
    fun getCalendar(): Calendar
}
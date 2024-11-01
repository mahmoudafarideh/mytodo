package com.ma.mytodo.ui.add

import com.ma.mytodo.utils.CalendarProvider
import java.util.Calendar

class FakeCalendarProvider : CalendarProvider {
    private val calendar = Calendar.getInstance()
    override fun getCalendar(): Calendar = calendar
}
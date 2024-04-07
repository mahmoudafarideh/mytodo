package com.ma.mytodo.ui.add

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test
import java.util.Calendar

class AddTodoViewModelTest {

    private fun createViewModel() = AddTodoViewModel()

    @Test
    fun `When view model created, initial title state should be null and should have no error`() {
        val viewModel = createViewModel()
        val title = viewModel.uiState.value.title
        assertNull(title.value)
        assertFalse(title.hasError)
    }

    @Test
    fun `When view model created, initial description state should be null and should have no error`() {
        val viewModel = createViewModel()
        val title = viewModel.uiState.value.description
        assertNull(title.value)
        assertFalse(title.hasError)
    }

    @Test
    fun `When view model created, initial repeat date state should be daily and hour and minute should be 0`() {
        val viewModel = createViewModel()
        val title = viewModel.uiState.value.dateTime
        assertEquals(TodoRepeatDateUiModel.Daily, title.repeatDate)
        assertEquals(TodoTimeUiModel(0, 0), title.time)
        assertFalse(title.hasError)
    }

    @Test
    fun `When title changed, the title state should get updated`() {
        val viewModel = createViewModel()
        viewModel.titleChanged("New Title")
        assertEquals("New Title", viewModel.uiState.value.title.value)
    }

    @Test
    fun `When description changed, the description state should get updated`() {
        val viewModel = createViewModel()
        viewModel.descriptionChanged("New Description")
        assertEquals("New Description", viewModel.uiState.value.description.value)
    }

    @Test
    fun `When time changed, time state should get updated`() {
        val viewModel = createViewModel()
        viewModel.timeChanged(22, 34)
        assertEquals(
            TodoTimeUiModel(22, 34),
            viewModel.uiState.value.dateTime.time
        )
    }

    @Test
    fun `Time hour should be from 0 to 23 and minute from 0 to 59`() {
        val viewModel = createViewModel()

        viewModel.timeChanged(24, 34)
        assertEquals(
            TodoTimeUiModel(23, 34),
            viewModel.uiState.value.dateTime.time
        )

        viewModel.timeChanged(-1, 34)
        assertEquals(
            TodoTimeUiModel(0, 34),
            viewModel.uiState.value.dateTime.time
        )

        viewModel.timeChanged(0, 60)
        assertEquals(
            TodoTimeUiModel(0, 59),
            viewModel.uiState.value.dateTime.time
        )

        viewModel.timeChanged(0, -1)
        assertEquals(
            TodoTimeUiModel(0, 0),
            viewModel.uiState.value.dateTime.time
        )
    }

    @Test
    fun `When switch to single repeat date clicked, repeat date should get updated to today date and time should be current time`() {
        val viewModel = createViewModel()
        viewModel.switchToSingleRepeatDateClicked()
        val calendar = Calendar.getInstance()
        assertEquals(
            TodoTimeUiModel(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE)),
            viewModel.uiState.value.dateTime.time
        )
        assertEquals(
            TodoRepeatDateUiModel.Single(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH)
            ),
            viewModel.uiState.value.dateTime.repeatDate
        )
    }

}
package com.ma.mytodo.ui.add

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test

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


}
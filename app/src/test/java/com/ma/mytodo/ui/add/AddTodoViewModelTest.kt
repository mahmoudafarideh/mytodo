package com.ma.mytodo.ui.add

import org.junit.Assert
import org.junit.Test

class AddTodoViewModelTest {

    private fun createViewModel() = AddTodoViewModel()

    @Test
    fun `when view model created, title state should be null and has error should be false`() {
        //Given
        val viewModel = createViewModel()

        //Then
        val title = viewModel.uiState.value.title
        Assert.assertNull(title.value)
        Assert.assertFalse(title.hasError)
    }

    @Test
    fun `when view model created, description state should be null and has error should be false`() {
        //Given
        val viewModel = createViewModel()

        //Then
        val title = viewModel.uiState.value.description
        Assert.assertNull(title.value)
        Assert.assertFalse(title.hasError)
    }

    @Test
    fun `when view model created, repeat date should be daily, hour and minute should be zero and has error should be false`() {
        //Given
        val viewModel = createViewModel()

        //Then
        val title = viewModel.uiState.value.dateTime
        Assert.assertEquals(TodoRepeatDateUiModel.Daily, title.repeatDate)
        Assert.assertEquals(TodoTimeUiModel(0, 0), title.time)
        Assert.assertFalse(title.hasError)
    }

    @Test
    fun `When title changed, the title value should be updated`() {
        //Given
        val viewModel = createViewModel()
        viewModel.titleChanged("Here")

        //Then
        Assert.assertEquals("Here", viewModel.uiState.value.title.value)
    }


}
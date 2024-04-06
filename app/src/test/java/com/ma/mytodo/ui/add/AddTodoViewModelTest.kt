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
        viewModel.titleChanged("Title")

        //Then
        Assert.assertEquals("Title", viewModel.uiState.value.title.value)
    }

    @Test
    fun `When description changed, the description value should be updated`() {
        //Given
        val viewModel = createViewModel()
        viewModel.descriptionChanged("Description")

        //Then
        Assert.assertEquals("Description", viewModel.uiState.value.description.value)
    }

    @Test
    fun `When time changed, the time value should be updated`() {
        //Given
        val viewModel = createViewModel()
        viewModel.timeChanged(12, 20)

        //Then
        Assert.assertEquals(TodoTimeUiModel(12, 20), viewModel.uiState.value.dateTime.time)
    }


    @Test
    fun `The time hour should be from 0 to 23 and minute should be 0 to 59`() {
        val viewModel = createViewModel()

        viewModel.timeChanged(-1, 20)
        Assert.assertEquals(TodoTimeUiModel(0, 20), viewModel.uiState.value.dateTime.time)

        viewModel.timeChanged(24, 20)
        Assert.assertEquals(TodoTimeUiModel(23, 20), viewModel.uiState.value.dateTime.time)

        viewModel.timeChanged(23,60)
        Assert.assertEquals(TodoTimeUiModel(23, 59), viewModel.uiState.value.dateTime.time)

        viewModel.timeChanged(23,-1)
        Assert.assertEquals(TodoTimeUiModel(23, 0), viewModel.uiState.value.dateTime.time)
    }


}
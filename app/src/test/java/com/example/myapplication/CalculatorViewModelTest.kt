package com.example.myapplication

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorViewModelTest {

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setup() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun `sequential calculation 2 plus 3 times 4 equals 20`() {
        viewModel.onNumberClick("2")
        viewModel.onOperationClick("+")
        viewModel.onNumberClick("3")
        viewModel.onOperationClick("*")
        viewModel.onNumberClick("4")
        viewModel.onEqualsClick()

        assertEquals("20", viewModel.uiState.value.displayValue)
    }

    @Test
    fun `division by zero returns Error`() {
        viewModel.onNumberClick("1")
        viewModel.onOperationClick("/")
        viewModel.onNumberClick("0")
        viewModel.onEqualsClick()

        assertEquals("Error", viewModel.uiState.value.displayValue)
    }

    @Test
    fun `trigonometric sin 90 degrees equals 1`() {
        viewModel.onNumberClick("90")
        viewModel.onTrigClick("sin")

        assertEquals("1", viewModel.uiState.value.displayValue)
    }

    @Test
    fun `clear resets state`() {
        viewModel.onNumberClick("5")
        viewModel.onOperationClick("+")
        viewModel.onNumberClick("5")
        viewModel.onClearClick()

        assertEquals("0", viewModel.uiState.value.displayValue)
        assertEquals("", viewModel.uiState.value.expression)
    }
}

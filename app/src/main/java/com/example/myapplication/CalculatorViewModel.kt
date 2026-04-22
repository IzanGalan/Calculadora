package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.*

data class CalculatorUiState(
    val displayValue: String = "0",
    val isDegreeMode: Boolean = true,
    val expression: String = ""
)

class CalculatorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()

    private var currentInput: String = ""
    private var runningResult: Double? = null
    private var pendingOperation: String? = null
    private var isNewInput: Boolean = true

    fun onNumberClick(number: String) {
        if (isNewInput) {
            currentInput = number
            isNewInput = false
        } else {
            if (currentInput == "0") {
                currentInput = number
            } else {
                currentInput += number
            }
        }
        updateDisplay()
    }

    fun onOperationClick(operation: String) {
        val inputValue = currentInput.toDoubleOrNull() ?: 0.0
        
        if (runningResult == null) {
            runningResult = inputValue
        } else if (pendingOperation != null) {
            runningResult = calculate(runningResult!!, inputValue, pendingOperation!!)
        }

        pendingOperation = operation
        isNewInput = true
        currentInput = runningResult.toString()
        updateDisplay()
    }

    fun onEqualsClick() {
        val inputValue = currentInput.toDoubleOrNull() ?: 0.0
        if (runningResult != null && pendingOperation != null) {
            runningResult = calculate(runningResult!!, inputValue, pendingOperation!!)
            currentInput = runningResult.toString()
            pendingOperation = null
            isNewInput = true
            updateDisplay()
        }
    }

    fun onClearClick() {
        currentInput = ""
        runningResult = null
        pendingOperation = null
        isNewInput = true
        _uiState.update { it.copy(displayValue = "0", expression = "") }
    }

    fun onTrigClick(op: String) {
        val inputValue = currentInput.toDoubleOrNull() ?: 0.0
        val angle = if (_uiState.value.isDegreeMode) Math.toRadians(inputValue) else inputValue
        
        val result = when (op.lowercase()) {
            "sin" -> sin(angle)
            "cos" -> cos(angle)
            "tan" -> tan(angle)
            else -> 0.0
        }
        
        currentInput = result.toString()
        isNewInput = true
        updateDisplay()
    }

    fun toggleAngleUnit() {
        _uiState.update { it.copy(isDegreeMode = !it.isDegreeMode) }
    }

    private fun calculate(a: Double, b: Double, op: String): Double {
        return when (op) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> if (b != 0.0) a / b else Double.NaN
            else -> b
        }
    }

    private fun updateDisplay() {
        _uiState.update { 
            it.copy(
                displayValue = formatResult(currentInput),
                expression = if (pendingOperation != null) "${formatResult(runningResult.toString())} $pendingOperation" else ""
            )
        }
    }

    private fun formatResult(value: String): String {
        val d = value.toDoubleOrNull() ?: return value
        if (d.isNaN()) return "Error"
        if (d.isInfinite()) return "Infinity"
        return if (d % 1.0 == 0.0) {
            d.toLong().toString()
        } else {
            // Limit to 8 decimal places for display
            "%.8f".format(d).trimEnd('0').trimEnd('.')
        }
    }
}

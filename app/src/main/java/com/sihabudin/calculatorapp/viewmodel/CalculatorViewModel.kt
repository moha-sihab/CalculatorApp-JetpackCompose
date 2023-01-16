package com.sihabudin.calculatorapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sihabudin.calculatorapp.core.Calculator
import com.sihabudin.calculatorapp.screen.CalculatorState
import com.sihabudin.calculatorapp.screen.OperationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(private val calculator: Calculator) : ViewModel() {
    var calculatorState by mutableStateOf(CalculatorState())
    val maxNumber = 8

    fun inputNumber(input: String) {
        if (calculatorState.operation == null) {
            if(calculatorState.input1.length >= maxNumber){
                return
            }
            calculatorState = calculatorState.copy(
                input1 = calculatorState.input1 + input,
                operationDisplay = calculatorState.operationDisplay + input
            )
            return
        }
        if(calculatorState.input2.length >= maxNumber){
            return
        }
        calculatorState = calculatorState.copy(
            input2 = calculatorState.input2 + input,
            operationDisplay = calculatorState.operationDisplay + input
        )
    }

    fun setOperation(operation: OperationState) {
        if (calculatorState.input1.isNotBlank() && calculatorState.input2.isBlank()) {
            if (calculatorState.operation == null && calculatorState.resultDisplay.isNotBlank()) {
                calculatorState = calculatorState.copy(
                    operation = operation,
                    operationDisplay = calculatorState.resultDisplay + operation.operation
                )
            } else {
                calculatorState = calculatorState.copy(
                    operation = operation,
                    operationDisplay = calculatorState.operationDisplay + operation.operation
                )
            }

            when (operation) {
                is OperationState.SplitRemainder -> {
                    calculatorState = calculatorState.copy(
                        hintInformation = "Please input some numbers, ex : 40.30.20, separate with point"
                    )
                }
                is OperationState.SplitEqual -> {
                    calculatorState = calculatorState.copy(
                        hintInformation = "Please input number that lower than your first input"
                    )
                }
                else -> {
                    calculatorState = calculatorState.copy(
                        hintInformation = ""
                    )
                }
            }

        }
    }

    fun inputDecimal() {
        if (calculatorState.operation == null && !calculatorState.input1.contains(".") && calculatorState.input1.isNotBlank()) {
            calculatorState = calculatorState.copy(
                input1 = calculatorState.input1 + ".",
                operationDisplay = calculatorState.operationDisplay + ".",
            )
            return
        } else if (!calculatorState.input2.contains(".") && calculatorState.input2.isNotBlank()) {
            calculatorState = calculatorState.copy(
                input2 = calculatorState.input2 + ".",
                operationDisplay = calculatorState.operationDisplay + ".",
            )
        } else if (calculatorState.operation == OperationState.SplitRemainder && calculatorState.input2.isNotBlank()) {
            calculatorState = calculatorState.copy(
                input2 = calculatorState.input2 + ".",
                operationDisplay = calculatorState.operationDisplay + ".",
            )
        }
    }

    fun calculate() {
        when (calculatorState.operation) {
            is OperationState.SplitRemainder -> {
                splitRemainderCalculate()
            }
            else -> {
                normalCalculate()
            }
        }
    }

    fun splitRemainderCalculate() {

        val totalNumber = calculatorState.input1.toDoubleOrNull()
        val splitNumber = calculatorState.input2
        val operation = calculatorState.operation

        if (operation != null && totalNumber != null && splitNumber.isNotBlank()) {
            var result = ""
            result = calculator.splitNumRemainder(
                totalNumber,
                splitNumber
            )
            calculatorState = calculatorState.copy(
                input1 = result.removeSuffix(".0"),
                input2 = "",
                operation = null,
                resultDisplay = result.removeSuffix(".0"),
            )
        }
    }

    fun normalCalculate() {
        val input1 = calculatorState.input1.toDoubleOrNull()
        val input2 = calculatorState.input2.toDoubleOrNull()
        val operation = calculatorState.operation

        if (input1 != null && input2 != null && operation != null) {
            var result = ""
            result = when (operation) {
                is OperationState.Add -> calculator.add(input1, input2).toString()
                is OperationState.Divide -> calculator.divide(input1, input2).toString()
                is OperationState.Multiply -> calculator.multiply(input1, input2).toString()
                is OperationState.Subtract -> calculator.substract(input1, input2).toString()
                is OperationState.SplitEqual -> calculator.splitEqual(input1, input2)
                else -> {
                    ""
                }
            }

            calculatorState = calculatorState.copy(
                input1 = result.removeSuffix(".0"),
                input2 = "",
                operation = null,
                resultDisplay = result.removeSuffix(".0"),
            )
        }
    }

    fun deleteInput() {
        when {
            calculatorState.input2.isNotBlank() -> {
                calculatorState = calculatorState.copy(
                    input2 = calculatorState.input2.dropLast(1),
                    operationDisplay = calculatorState.operationDisplay.dropLast(1)
                )
            }
            calculatorState.operation != null -> {
                calculatorState = calculatorState.copy(
                    operation = null,
                    operationDisplay = calculatorState.operationDisplay.dropLast(1)
                )
            }
            calculatorState.input1.isNotBlank() -> {
                calculatorState = calculatorState.copy(
                    input1 = calculatorState.input1.dropLast(1),
                    operationDisplay = calculatorState.operationDisplay.dropLast(1)
                )
            }
        }
    }

    fun clearState() {
        calculatorState = calculatorState.copy(
            input1 = "",
            input2 = "",
            operation = null,
            resultDisplay = "",
            operationDisplay = ""
        )
    }

}